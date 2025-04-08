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
        return Json.write(this, Json.Formatting.PRETTY);
    }

    public String value() {
        return this.value;
    }
}
