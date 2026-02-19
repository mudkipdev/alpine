package alpine.json;

import alpine.json.codec.Codec;
import alpine.json.codec.Transcoder;
import org.junit.jupiter.api.Test;

import static alpine.json.Element.object;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class CodecBuilderTest {
    record User(String name, int age) {
        static final Codec<User> CODEC = Codec.<User>builder()
                .with("name", Codec.STRING, User::name)
                .with("age", Codec.INTEGER, User::age)
                .build(User::new);
    }

    @Test
    void testUser() {
        var actual = User.CODEC.encode(Transcoder.JSON, new User("Steve", 67));
        var expected = object().set("name", "Steve").set("age", 67);
        assertEquals(actual, expected);
    }
}
