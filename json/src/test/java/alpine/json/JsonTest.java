package alpine.json;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static alpine.json.Element.*;
import static org.junit.jupiter.api.Assertions.*;

final class JsonTest {
    @Test
    @Disabled("max stack size varies on different jvms")
    void testRecursion() {
        try {
            var element = Json.read("[".repeat(2000) + "]".repeat(2000));
            var maxDepth = traverseRecursively(element, 0) + 1;
            assert maxDepth == 2000;
        } catch (ParsingException e) {
            throw new RuntimeException(e);
        }
    }

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

    @ParameterizedTest(name = "{0} (encoding)")
    @MethodSource("arguments")
    void testEncoding(String label, Element element, String expected) {
        var actual = Json.write(element, Json.Formatting.PRETTY);
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

    private static int traverseRecursively(Element element, int currentDepth) {
        return switch (element) {
            case ArrayElement array -> {
                var depth = new AtomicInteger(currentDepth);
                array.forEach(child -> depth.set(Math.max(depth.get(), traverseRecursively(child, currentDepth + 1))));
                yield depth.get();
            }

            case ObjectElement object -> {
                var depth = new AtomicInteger(currentDepth);
                object.each((key, value) -> depth.set(Math.max(depth.get(), traverseRecursively(value, currentDepth + 1))));
                yield depth.get();
            }

            default -> currentDepth;
        };
    }
}
