package alpine.json;

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
        return Json.write(this, Json.Formatting.PRETTY);
    }

    public double value() {
        return this.value;
    }
}
