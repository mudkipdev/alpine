package alpine.json;

import alpine.json.codec.Codec;
import alpine.json.codec.Transcoder;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static alpine.json.Element.*;
import static org.junit.jupiter.api.Assertions.*;

final class JsonCodecTest {
    record User(String name, int age, List<String> friends) {
        static final Codec<User> CODEC = Codec.<User>builder()
                .with("name", Codec.STRING, User::name)
                .with("age", Codec.INTEGER, User::age)
                .with("friends", Codec.STRING.list(), User::friends)
                .build(User::new);
    }

    enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    @Test
    void testObjectCodec() {
        var user = new User("Steve", 67, List.of("Alex"));
        var element = object().set("name", "Steve").set("age", 67).set("friends", array("Alex"));
        assertEquals(element, User.CODEC.encode(Transcoder.JSON, user));
        assertEquals(user, User.CODEC.decode(Transcoder.JSON, element));
    }

    @Test
    void testList() {
        var codec = Codec.INTEGER.list();
        assertEquals(array(1, 2, 3), codec.encode(Transcoder.JSON, List.of(1, 2, 3)));
        assertEquals(List.of(1, 2, 3), codec.decode(Transcoder.JSON, array(1, 2, 3)));
        assertEquals(array(), codec.encode(Transcoder.JSON, List.of()));
        assertEquals(List.of(), codec.decode(Transcoder.JSON, array()));
    }

    @Test
    void testNullable() {
        var codec = Codec.STRING.nullable();
        assertEquals(string("hello"), codec.encode(Transcoder.JSON, "hello"));
        assertEquals(nil(), codec.encode(Transcoder.JSON, null));
        assertEquals("hello", codec.decode(Transcoder.JSON, string("hello")));
        assertNull(codec.decode(Transcoder.JSON, nil()));
    }

    @Test
    void testOptional() {
        var codec = Codec.STRING.optional();
        assertEquals(string("hello"), codec.encode(Transcoder.JSON, Optional.of("hello")));
        assertEquals(nil(), codec.encode(Transcoder.JSON, Optional.empty()));
        assertEquals(Optional.of("hello"), codec.decode(Transcoder.JSON, string("hello")));
        assertEquals(Optional.empty(), codec.decode(Transcoder.JSON, nil()));
    }

    @Test
    void testMap() {
        var codec = Codec.STRING.map(Integer::parseInt, String::valueOf);
        assertEquals(string("42"), codec.encode(Transcoder.JSON, 42));
        assertEquals(42, codec.decode(Transcoder.JSON, string("42")));
    }

    @Test
    void testMapCodec() {
        var codec = Codec.map(Codec.INTEGER);
        var encoded = codec.encode(Transcoder.JSON, Map.of("a", 1, "b", 2));
        assertInstanceOf(ObjectElement.class, encoded);
        var object = (ObjectElement) encoded;
        assertEquals(number(1), object.get("a"));
        assertEquals(number(2), object.get("b"));
        assertEquals(Map.of("a", 1, "b", 2), codec.decode(Transcoder.JSON, encoded));
    }

    @Test
    void testPrimitiveCodecs() {
        assertEquals(bool(true), Codec.BOOLEAN.encode(Transcoder.JSON, true));
        assertEquals(true, Codec.BOOLEAN.decode(Transcoder.JSON, bool(true)));

        assertEquals(number(127), Codec.BYTE.encode(Transcoder.JSON, (byte) 127));
        assertEquals((byte) 127, Codec.BYTE.decode(Transcoder.JSON, number(127)));

        assertEquals(number(1000), Codec.SHORT.encode(Transcoder.JSON, (short) 1000));
        assertEquals((short) 1000, Codec.SHORT.decode(Transcoder.JSON, number(1000)));

        assertEquals(number(42), Codec.INTEGER.encode(Transcoder.JSON, 42));
        assertEquals(42, Codec.INTEGER.decode(Transcoder.JSON, number(42)));

        assertEquals(number(123456789L), Codec.LONG.encode(Transcoder.JSON, 123456789L));
        assertEquals(123456789L, Codec.LONG.decode(Transcoder.JSON, number(123456789)));

        assertEquals(number(1.5f), Codec.FLOAT.encode(Transcoder.JSON, 1.5f));
        assertEquals(1.5f, Codec.FLOAT.decode(Transcoder.JSON, number(1.5)));

        assertEquals(number(3.14), Codec.DOUBLE.encode(Transcoder.JSON, 3.14));
        assertEquals(3.14, Codec.DOUBLE.decode(Transcoder.JSON, number(3.14)));

        assertEquals(string("hi"), Codec.STRING.encode(Transcoder.JSON, "hi"));
        assertEquals("hi", Codec.STRING.decode(Transcoder.JSON, string("hi")));

        assertEquals(string("Z"), Codec.CHARACTER.encode(Transcoder.JSON, 'Z'));
        assertEquals('Z', Codec.CHARACTER.decode(Transcoder.JSON, string("Z")));
    }

    @Test
    void testArray() {
        var codec = Codec.STRING.array(String[]::new);
        assertEquals(array("a", "b", "c"), codec.encode(Transcoder.JSON, new String[] {"a", "b", "c"}));
        assertArrayEquals(new String[] {"x", "y"}, codec.decode(Transcoder.JSON, array("x", "y")));
    }

    @Test
    void testPrimitiveArrayCodecs() {
        var booleans = new boolean[] {true, false, true};
        assertArrayEquals(booleans, Codec.BOOLEAN_ARRAY.decode(Transcoder.JSON, Codec.BOOLEAN_ARRAY.encode(Transcoder.JSON, booleans)));

        var integers = new int[] {1, 2, 3};
        assertArrayEquals(integers, Codec.INTEGER_ARRAY.decode(Transcoder.JSON, Codec.INTEGER_ARRAY.encode(Transcoder.JSON, integers)));

        var longs = new long[] {100L, 200L};
        assertArrayEquals(longs, Codec.LONG_ARRAY.decode(Transcoder.JSON, Codec.LONG_ARRAY.encode(Transcoder.JSON, longs)));

        var doubles = new double[] {1.1, 2.2};
        assertArrayEquals(doubles, Codec.DOUBLE_ARRAY.decode(Transcoder.JSON, Codec.DOUBLE_ARRAY.encode(Transcoder.JSON, doubles)));
    }

    @Test
    void testEnumName() {
        var codec = Codec.name(Direction.class);
        assertEquals(string("north"), codec.encode(Transcoder.JSON, Direction.NORTH));
        assertEquals(Direction.SOUTH, codec.decode(Transcoder.JSON, string("south")));

        for (var direction : Direction.values()) {
            assertEquals(direction, codec.decode(Transcoder.JSON, codec.encode(Transcoder.JSON, direction)));
        }

        assertThrows(Exception.class, () -> codec.decode(Transcoder.JSON, string("up")));
    }

    @Test
    void testUuid() {
        var uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        assertEquals(string("550e8400-e29b-41d4-a716-446655440000"), Codec.UUID.encode(Transcoder.JSON, uuid));
        assertEquals(uuid, Codec.UUID.decode(Transcoder.JSON, string("550e8400-e29b-41d4-a716-446655440000")));
        var random = UUID.randomUUID();
        assertEquals(random, Codec.UUID.decode(Transcoder.JSON, Codec.UUID.encode(Transcoder.JSON, random)));
    }
}
