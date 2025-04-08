package alpine.json;

public final class NullElement implements Element {
    static final NullElement INSTANCE = new NullElement();

    private NullElement() {

    }

    @Override
    public String toString() {
        return Json.write(this, Json.Formatting.PRETTY);
    }
}
