package alpine.json;

/**
 * A JSON element which can represent a {@code true} or {@code false} value.
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8259#section-3">RFC 8259</a>
 * @author mudkip
 */
public final class BooleanElement implements Element {
    private final boolean value;

    BooleanElement(boolean value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(this.value);
    }

    @Override
    public boolean equals(Object object) {
        return this == object || (object instanceof BooleanElement element
                && element.value == this.value);
    }

    @Override
    public String toString() {
        return Json.write(this, Json.Formatting.INLINE_PRETTY);
    }

    public boolean value() {
        return this.value;
    }
}
