package alpine.json;

/**
 * A JSON element which can represent both integers and fractional numbers.
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8259#section-6">RFC 8259</a>
 * @author mudkip
 */
public final class NumberElement implements Element {
    private final double value;

    NumberElement(double value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(this.value);
    }

    @Override
    public boolean equals(Object object) {
        return this == object || (object instanceof NumberElement element
                && element.value == this.value);
    }

    @Override
    public String toString() {
        return Json.write(this, JsonFormatting.INLINE);
    }

    public double value() {
        return this.value;
    }
}
