package alpine.json;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

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

    public @Nullable Element get(String key, Element fallback) {
        return this.elements.getOrDefault(key, fallback);
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

    public boolean has(String key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null!");
        return this.elements.containsKey(key);
    }

    public boolean has(Element value) {
        if (value == null) throw new IllegalArgumentException("Value cannot be null!");
        return this.elements.containsValue(value);
    }

    public boolean has(Boolean value) {
        return this.has(Element.bool(value));
    }

    public boolean has(Number value) {
        return this.has(Element.number(value));
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
        if (!this.has(key)) {
            throw new IllegalStateException("Key \"" + key + "\" is not present!");
        }

        return this.copy(map -> map.remove(key));
    }

    public ObjectElement clear() {
        return this.copy(Map::clear);
    }

    private ObjectElement copy(Consumer<Map<String, Element>> mutator) {
        if (mutator == null) throw new IllegalArgumentException("Mutator cannot be null!");
        var map = new HashMap<>(this.elements);
        mutator.accept(map);
        return new ObjectElement(map);
    }
}
