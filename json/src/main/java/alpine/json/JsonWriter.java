package alpine.json;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static alpine.json.Json.*;

final class JsonWriter {
    private static final String ESCAPED = ""
            + QUOTE + BACKSLASH + BACKSPACE + FORM_FEED
            + LINE_FEED + CARRIAGE_RETURN + TAB;

    String write(Element value, Formatting formatting) {
        var builder = new StringBuilder();

        switch (value) {
            case NullElement ignored -> builder.append(NULL);
            case BooleanElement element -> builder.append(element.value() ? TRUE : FALSE);
            case NumberElement element -> this.writeNumber(builder, element.value());
            case StringElement element -> this.writeString(builder, element.value());
            case ArrayElement element -> this.writeArray(builder, element, formatting);
            case ObjectElement element -> this.writeObject(builder, element, formatting);
        };

        return builder.toString();
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

        for (var character : string.toCharArray()) {
            if (CHARACTER_TO_ESCAPE.containsKey(character)) {
                builder.append(BACKSLASH).append(CHARACTER_TO_ESCAPE.get(character));
            } else if (Character.isISOControl(character)) {
                builder.append(String.format("\\u%04X", (int) character));
            } else {
                builder.append(character);
            }
        }

        builder.append(QUOTE);
    }

    private void writeArray(StringBuilder builder, ArrayElement element, Formatting formatting) {
        builder
                .append(BEGIN_ARRAY)
                .append(element.stream()
                    .map(value -> this.write(value, formatting))
                    .collect(Collectors.joining(formatting.comma())))
                .append(END_ARRAY);
    }

    private void writeObject(StringBuilder builder, ObjectElement element, Formatting formatting) {
        var firstElement = new AtomicBoolean(true);
        builder.append(BEGIN_OBJECT);

        element.each((key, value) -> {
            builder.append(firstElement.get() ? "" : formatting.comma());
            this.writeString(builder, key);
            firstElement.set(false);
            builder.append(formatting.colon()).append(this.write(value, formatting));
        });

        builder.append(END_OBJECT);
    }
}
