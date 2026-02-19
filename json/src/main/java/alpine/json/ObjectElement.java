package alpine.json;

import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * A JSON element which stores a collection of key-value pairs where the keys are strings.
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8259#section-4">RFC 8259</a>
 * @author mudkip
 */
public final class ObjectElement implements Element {
    private final Map<String, Element> elements;

    ObjectElement() {
        this(new LinkedHashMap<>());
    }

    ObjectElement(Map<String, Element> elements) {
        this.elements = elements;
    }

    @Override
    public int hashCode() {
        return this.elements.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return this == object || (object instanceof ObjectElement element
                && Objects.equals(this.elements, element.elements));
    }

    @Override
    public String toString() {
        return Json.write(this, Json.Formatting.PRETTY);
    }

    public Stream<Map.Entry<String, Element>> stream() {
        return this.elements.entrySet().stream();
    }

    public boolean empty() {
        return this.length() < 1;
    }

    public int length() {
        return this.elements.size();
    }

    public @Nullable Element get(String key) {
        return this.elements.get(key);
    }

    public @Nullable String getString(String key) {
        return this.getString(key, null);
    }

    public @Nullable Number getNumber(String key) {
        return this.getNumber(key, null);
    }

    public byte getByte(String key) {
        return (byte) this.getNumber(key, 0);
    }

    public short getShort(String key) {
        return (short) this.getNumber(key, 0);
    }

    public int getInteger(String key) {
        return (int) this.getNumber(key, 0);
    }

    public long getLong(String key) {
        return (long) this.getNumber(key, 0);
    }

    public float getFloat(String key) {
        return (float) this.getNumber(key, 0.0F);
    }

    public double getDouble(String key) {
        return (double) this.getNumber(key, 0.0D);
    }

    public @Nullable Boolean getBoolean(String key) {
        return this.elements.get(key) instanceof BooleanElement element
                ? element.value() : null;
    }

    public @Nullable Element get(String key, Element fallback) {
        return this.elements.getOrDefault(key, fallback);
    }

    public String getString(String key, String fallback) {
        return this.elements.get(key) instanceof StringElement element
                ? element.value() : fallback;
    }

    public Number getNumber(String key, Number fallback) {
        return this.elements.get(key) instanceof NumberElement element
                ? element.value() : fallback;
    }

    public byte getByte(String key, byte fallback) {
        return this.elements.get(key) instanceof NumberElement element
                ? (byte) element.value() : fallback;
    }

    public short getShort(String key, short fallback) {
        return this.elements.get(key) instanceof NumberElement element
                ? (short) element.value() : fallback;
    }

    public int getInteger(String key, int fallback) {
        return this.elements.get(key) instanceof NumberElement element
                ? (int) element.value() : fallback;
    }

    public long getLong(String key, long fallback) {
        return this.elements.get(key) instanceof NumberElement element
                ? (long) element.value() : fallback;
    }

    public float getFloat(String key, float fallback) {
        return this.elements.get(key) instanceof NumberElement element
                ? (float) element.value() : fallback;
    }

    public double getDouble(String key, double fallback) {
        return this.elements.get(key) instanceof NumberElement element
                ? element.value() : fallback;
    }

    public boolean getBoolean(String key, boolean fallback) {
        return this.elements.get(key) instanceof BooleanElement element
                ? element.value() : fallback;
    }

    @SuppressWarnings("unchecked")
    public <T extends Element> T get(String key, Class<T> clazz, T fallback) {
        var element = this.elements.get(key);

        if (clazz.isInstance(element)) {
            return (T) element;
        } else {
            return fallback;
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Element> T expect(String key, Class<T> clazz) {
        var element = this.elements.get(key);

        if (element == null) {
            throw new AssertionError("Expected an element for \"" + key + "\"!");
        } else if (!clazz.isInstance(element)) {
            throw new AssertionError("Expected element to be a " + clazz.getSimpleName() + "!");
        } else return (T) element;
    }

    public boolean hasKey(String key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null!");
        return this.elements.containsKey(key);
    }

    public boolean hasValue(Element value) {
        if (value == null) throw new IllegalArgumentException("Value cannot be null!");
        return this.elements.containsValue(value);
    }

    public boolean hasString(String value) {
        return this.hasValue(Element.string(value));
    }

    public boolean hasValue(Number value) {
        return this.hasValue(Element.number(value));
    }

    public boolean hasValue(Boolean value) {
        return this.hasValue(Element.bool(value));
    }

    public void each(BiConsumer<String, Element> consumer) {
        if (consumer == null) {
            throw new IllegalArgumentException("Consumer cannot be null!");
        }

        this.elements.forEach(consumer);
    }

    public ObjectElement set(String key, Element value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null!");
        } else if (value == null) {
            throw new IllegalArgumentException("Value cannot be null!");
        }

        return this.copy(map -> map.put(key, value));
    }

    public ObjectElement set(String key, boolean value) {
        return this.set(key, Element.bool(value));
    }

    public ObjectElement set(String key, Number value) {
        return this.set(key, Element.number(value));
    }

    public ObjectElement set(String key, String value) {
        return this.set(key, Element.string(value));
    }

    public ObjectElement remove(String key) {
        if (!this.hasKey(key)) {
            throw new IllegalStateException("Key \"" + key + "\" is not present!");
        }

        return this.copy(map -> map.remove(key));
    }

    public ObjectElement clear() {
        return this.copy(Map::clear);
    }

    public ObjectElement copy(Consumer<Map<String, Element>> mutator) {
        if (mutator == null) throw new IllegalArgumentException("Mutator cannot be null!");
        var map = new LinkedHashMap<>(this.elements);
        mutator.accept(map);
        return new ObjectElement(map);
    }
}
