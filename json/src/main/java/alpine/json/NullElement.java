package alpine.json;

/**
 * A JSON element which represents the absence of a value.
 * <p>
 * This element can only be represented as {@code null} in encoded form.
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8259#section-3">RFC 8259</a>
 * @author mudkip
 */
public final class NullElement implements Element {
    static final NullElement INSTANCE = new NullElement();

    private NullElement() {

    }

    @Override
    public String toString() {
        return Json.write(this, Json.Formatting.PRETTY);
    }
}
