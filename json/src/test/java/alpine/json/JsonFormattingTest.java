package alpine.json;

import org.junit.jupiter.api.Test;

import static alpine.json.Element.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class JsonFormattingTest {
    private static final ObjectElement OBJECT = object()
            .set("a", "b")
            .set("c", "d")
            .set("nested", object().set("x", 1));

    @Test
    void testCompact() {
        assertEquals(
                "{\"a\":\"b\",\"c\":\"d\",\"nested\":{\"x\":1}}",
                Json.write(OBJECT, JsonFormatting.COMPACT));
    }

    @Test
    void testInline() {
        assertEquals(
                "{\"a\": \"b\", \"c\": \"d\", \"nested\": {\"x\": 1}}",
                Json.write(OBJECT, JsonFormatting.INLINE));
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
        }""", Json.write(OBJECT, JsonFormatting.PRETTY));
    }
}
