package alpine.json;

import java.util.Objects;

public final class StringElement implements Element {
    private final String value;

    StringElement(String value) {
        if (value == null) {
            throw new IllegalArgumentException("String value cannot be null!");
        }

        this.value = value;
    }

    private static String prettyPrint(String string) {
        return string
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t")
                .replace("\"", "\\\"");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return this == object || (object instanceof StringElement element
                && Objects.equals(this.value, element.value));
    }

    @Override
    public String toString() {
        return prettyPrint(Json.write(this, Json.Formatting.PRETTY));
    }

    public String value() {
        return value;
    }
}
