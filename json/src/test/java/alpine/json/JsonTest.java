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
        var testCases = new String[] {
                "test",
                "{", "}", "[", "]",
                "\"", "\"\0\"",
                "123abc", "[123e]", "0.", ".0"
        };

        for (var string : testCases) {
            assertThrows(ParsingException.class, () -> Json.read(string));
        }
    }

    @ParameterizedTest(name = "{0} (encoding)")
    @MethodSource("arguments")
    void testEncoding(String label, Element element, String expected) {
        var actual = Json.write(element, Json.Formatting.PRETTY);
        assert Objects.equals(actual, expected) : String.format(
                "Expected %s while encoding, got %s.", expected, actual);
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

                // Arrays
                Arguments.of("Empty Array", array(), "[]"),
                Arguments.of("Number Array", array(1, 2, 3), "[1, 2, 3]"),
                Arguments.of("String Array", array("a", "b", "c"), "[\"a\", \"b\", \"c\"]"),

                // Objects
                Arguments.of("Empty Object", object(), "{}"),
                Arguments.of("Simple Object", object().set("a", 1).set("b", 2), "{\"a\": 1, \"b\": 2}"));
    }
}
