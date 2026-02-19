package alpine.json;

import org.junit.jupiter.api.Test;

import static alpine.json.Element.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class FormattingTest {
    private static final ObjectElement OBJECT = object()
            .set("a", "b")
            .set("c", "d")
            .set("nested", object().set("x", 1));

    @Test
    void testCompact() {
        assertEquals(
                "{\"a\":\"b\",\"c\":\"d\",\"nested\":{\"x\":1}}",
                Json.write(OBJECT, Json.Formatting.COMPACT));
    }

    @Test
    void testInlinePretty() {
        assertEquals(
                "{\"a\": \"b\", \"c\": \"d\", \"nested\": {\"x\": 1}}",
                Json.write(OBJECT, Json.Formatting.INLINE_PRETTY));
    }

    @Test
    void testPretty() {
        assertEquals("""
        {
            "a": "b",
            "c": "d",
            "nested": {
                "x": 1
            }
        }""", Json.write(OBJECT, Json.Formatting.PRETTY));
    }
}
