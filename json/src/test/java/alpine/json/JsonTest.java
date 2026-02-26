package alpine.json;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.stream.Stream;

import static alpine.json.Element.*;
import static org.junit.jupiter.api.Assertions.*;

final class JsonTest {
    @Test
    void testInvalid() {
        for (var string : new String[] {
            "test",
            "{", "}", "[", "]",
            "\"", "\"\0\"",
            "123abc", "[123e]", "0.", ".0"
        }) {
            assertThrows(ParsingException.class, () -> Json.read(string));
        }
    }

    @Test
    void testArrayAccess() {
        assert array().empty();
        assert array(true).get(0) instanceof BooleanElement;
        assert array(true).expect(0, BooleanElement.class).value();
        assertThrows(IndexOutOfBoundsException.class, () -> array().get(69));
    }

    @Test
    void testArrayInsertion() {
        assert array().append("a").length() == 1;
        assert array().append("b").insert(0, true).first() instanceof BooleanElement;
    }

    @Test
    void testArrayRemoval() {
        assert array(1, 2, 3).clear().empty();
        assert Objects.equals(array("a", "b", "c").remove(0), array("b", "c"));
        assert Objects.equals(array("a", "b", "c").removeValue("b"), array("a", "c"));
        assertThrows(IndexOutOfBoundsException.class, () -> array().remove(1));
    }

    @Test
    void testArrayContains() {
        assert !array().has(nil());
        assert array(1, 2, 3).has(1);
        assert array("a", "b").has("b");
    }

    @Test
    void testArrayReversal() {
        assertEquals(array(1, 3, 2), array(2, 3, 1).reverse());
    }

    @Test
    void testObjectAccess() {
        assert object().empty();
    }

    @Test
    void testObjectSetting() {
        object().set("key", "value").expect("key", StringElement.class);
        assertThrows(AssertionError.class, () -> object().set("key", "value").expect("key", NullElement.class));
    }

    @Test
    void testObjectContains() {
        assert !object().hasKey("key");
        assert !object().hasValue(false);
        assert object().set("key", "value").hasKey("key");
    }

    @Test
    void testObjectRemoval() {
        assert object().set("key", "value").remove("key").empty();
    }

    @Test
    void testObjectSorting() {
        var unsortedObject = object().set("b", 2).set("a", 1);
        var sortedObject = object().set("a", 1).set("b", 2);
        assertEquals(unsortedObject, sortedObject);
        assertNotEquals(unsortedObject.toString(), sortedObject.toString());
        assertEquals(unsortedObject.sort().toString(), sortedObject.toString());
    }

    @ParameterizedTest(name = "{0} (encoding)")
    @MethodSource("arguments")
    void testEncoding(String label, Element element, String expected) {
        var actual = Json.write(element, JsonFormatting.INLINE);
        assert Objects.equals(actual, expected) : String.format(
                "Expected %s while encoding, got %s.", '"' + expected + '"', actual);
    }

    @ParameterizedTest(name = "{0} (decoding)")
    @MethodSource("arguments")
    void testDecoding(String label, Element element, String input) throws ParsingException {
        var actual = Json.read(input);
        assert Objects.equals(actual, element) : String.format(
                "Expected %s while decoding, got %s.", element, actual);
    }

    static Stream<Arguments> arguments() {
        return Stream.of(
                // Literals
                Arguments.of("Null", nil(), "null"),
                Arguments.of("True", bool(true), "true"),
                Arguments.of("False", bool(false), "false"),

                // Numbers
                Arguments.of("Integer Number", number(69), "69"),
                Arguments.of("Decimal Number", number(1.23), "1.23"),
//                Arguments.of("Exponent Number", number(-1.23456789E+15), "-1.23456789E+15"),

                // Strings
                Arguments.of("String", string("Hello world!"), "\"Hello world!\""),
                Arguments.of("Escaped String", string("\n\t\r\b"), "\"\\n\\t\\r\\b\""),
                Arguments.of("Unicode String", string("\0"), "\"\\u0000\""),

                // Arrays
                Arguments.of("Empty Array", array(), "[]"),
                Arguments.of("Number Array", array(1, 2, 3), "[1, 2, 3]"),
                Arguments.of("String Array", array("a", "b", "c"), "[\"a\", \"b\", \"c\"]"),

                // Objects
                Arguments.of("Empty Object", object(), "{}"),
                Arguments.of("Simple Object", object().set("a", 1).set("b", 2), "{\"a\": 1, \"b\": 2}"));
    }
}
