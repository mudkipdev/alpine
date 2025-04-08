package alpine.json;

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
        return Json.write(this, Json.Formatting.PRETTY);
    }

    public boolean value() {
        return this.value;
    }
}
