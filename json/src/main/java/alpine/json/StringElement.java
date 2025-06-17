package alpine.json;

import java.util.Objects;

/**
 * A JSON element which can store a sequence of Unicode characters.
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8259#section-7">RFC 8259</a>
 * @author mudkip
 */
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
