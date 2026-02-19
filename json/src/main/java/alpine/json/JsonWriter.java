package alpine.json;

import org.jetbrains.annotations.ApiStatus;

import static alpine.json.JsonUtility.*;

@ApiStatus.Internal
final class JsonWriter {
    private static final char[] HEX_CHARACTERS = "0123456789ABCDEF".toCharArray();

    String write(Element value, Json.Formatting formatting) {
        // pre-size string builder for performance
        var builder = new StringBuilder(switch (value) {
            case ObjectElement element -> 64 * element.length();
            case ArrayElement element  -> 16 * element.length();
            default -> 32;
        });

        this.write(builder, value, formatting);
        return builder.toString();
    }

    private void write(StringBuilder builder, Element value, Json.Formatting formatting) {
        switch (value) {
            case NullElement _ -> builder.append(NULL);
            case BooleanElement element -> builder.append(element.value() ? TRUE : FALSE);
            case NumberElement element -> this.writeNumber(builder, element.value());
            case StringElement element -> this.writeString(builder, element.value());
            case ArrayElement element -> this.writeArray(builder, element, formatting);
            case ObjectElement element -> this.writeObject(builder, element, formatting);
        }
    }

    private void writeNumber(StringBuilder builder, double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("NaN and infinite numbers are not allowed!");
        }

        if (value == Math.rint(value)) {
            builder.append((long) value);
        } else {
            builder.append(value);
        }
    }

    private void writeString(StringBuilder builder, String string) {
        builder.append(QUOTE);

        var start = 0;
        var length = string.length();

        for (var index = 0; index < length; index++) {
            var character = string.charAt(index);

            // fast path: safe character
            if (character != '"' && character != '\\' && !Character.isISOControl(character)) {
                continue;
            }

            if (index > start) {
                builder.append(string, start, index);
            }

            switch (character) {
                case '"' -> builder.append("\\\"");
                case '\\' -> builder.append("\\\\");
                case '\b' -> builder.append("\\b");
                case '\f' -> builder.append("\\f");
                case '\n' -> builder.append("\\n");
                case '\r' -> builder.append("\\r");
                case '\t' -> builder.append("\\t");

                default -> {
                    builder.append("\\u");
                    builder.append(HEX_CHARACTERS[(character >> 12) & 0xF]);
                    builder.append(HEX_CHARACTERS[(character >> 8) & 0xF]);
                    builder.append(HEX_CHARACTERS[(character >> 4) & 0xF]);
                    builder.append(HEX_CHARACTERS[character & 0xF]);
                }
            }

            start = index + 1;
        }

        if (start < length) {
            builder.append(string, start, length);
        }

        builder.append(QUOTE);
    }

    private void writeArray(StringBuilder builder, ArrayElement element, Json.Formatting formatting) {
        builder.append(BEGIN_ARRAY);
        var firstElement = true;

        for (var value : element) {
            if (!firstElement) {
                builder.append(formatting.comma());
            }

            this.write(builder, value, formatting);
            firstElement = false;
        }

        builder.append(END_ARRAY);
    }

    private void writeObject(StringBuilder builder, ObjectElement element, Json.Formatting formatting) {
        builder.append(BEGIN_OBJECT);
        var firstElement = new boolean[] { true };

        element.each((key, value) -> {
            if (firstElement[0]) {
                firstElement[0] = false;
            } else {
                builder.append(formatting.comma());
            }

            this.writeString(builder, key);
            builder.append(formatting.colon());
            this.write(builder, value, formatting);
        });

        builder.append(END_OBJECT);
    }
}
