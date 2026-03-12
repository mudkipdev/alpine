package alpine.json;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static alpine.json.Element.*;

/**
 * A JSON element which in itself stores an ordered sequence of elements.
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8259#section-5">RFC 8259</a>
 * @author mudkip
 */
public final class ArrayElement implements Element, Iterable<Element> {
    private final List<Element> elements;

    ArrayElement() {
        this(new ArrayList<>());
    }

    ArrayElement(List<Element> elements) {
        this.elements = elements;
    }

    @Override
    public int hashCode() {
        return this.elements.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return this == object || (object instanceof ArrayElement element
                && Objects.equals(this.elements, element.elements));
    }

    @Override
    public String toString() {
        return Json.write(this, JsonFormatting.INLINE);
    }

    @Override
    public @NotNull Iterator<Element> iterator() {
        return this.elements.iterator();
    }

    public Stream<Element> stream() {
        return this.elements.stream();
    }

    /**
     * Returns whether the array is empty or not.
     * @return {@code true} if the length of the array is at least 1, otherwise {@code false}.
     */
    public boolean empty() {
        return this.length() < 1;
    }

    /**
     * Returns the amount of elements that are contained in this array.
     * @return The amount of elements that are contained in this array.
     */
    public int length() {
        return this.elements.size();
    }

    public @Nullable Element get(int index) {
        return this.elements.get(index);
    }

    @SuppressWarnings("unchecked")
    public <T extends Element> T expect(int index, Class<T> clazz) {
        var element = this.elements.get(index);

        if (element == null) {
            throw new AssertionError("Expected an element at " + index + "!");
        } else if (!clazz.isInstance(element)) {
            throw new AssertionError("Expected element to be a " + clazz.getSimpleName() + "!");
        } else return (T) element;
    }

    public @Nullable Element first() {
        return this.elements.getFirst();
    }

    public @Nullable Element last() {
        return this.elements.getLast();
    }

    public boolean has(Element element) {
        if (element == null) throw new IllegalArgumentException("Element cannot be null!");
        return this.elements.contains(element);
    }

    public boolean has(boolean value) {
        return this.has(bool(value));
    }

    public boolean has(Number value) {
        return this.has(number(value));
    }

    public boolean has(String value) {
        return this.has(string(value));
    }

    public void each(Consumer<Element> consumer) {
        if (consumer == null) {
            throw new IllegalArgumentException("Consumer cannot be null!");
        }

        this.elements.forEach(consumer);
    }

    public boolean all(Predicate<Element> predicate) {
        if (predicate == null) {
            throw new IllegalArgumentException("Predicate cannot be null!");
        }

        for (var element : this.elements) {
            if (!predicate.test(element)) {
                return false;
            }
        }

        return true;
    }

    public boolean any(Predicate<Element> predicate) {
        if (predicate == null) {
            throw new IllegalArgumentException("Predicate cannot be null!");
        }

        for (var element : this.elements) {
            if (predicate.test(element)) {
                return true;
            }
        }

        return false;
    }

    public ArrayElement append(Element element) {
        if (element == null) throw new IllegalArgumentException("Element cannot be null!");
        return this.copy(list -> list.add(element));
    }

    public ArrayElement append(boolean element) {
        return this.append(bool(element));
    }

    public ArrayElement append(Number element) {
        return this.append(number(element));
    }

    public ArrayElement append(String element) {
        return this.append(string(element));
    }

    public ArrayElement set(int index, Element element) {
        if (element == null) throw new IllegalArgumentException("Element cannot be null!");
        return this.copy(list -> list.set(index, element));
    }

    public ArrayElement set(int index, boolean element) {
        return this.set(index, bool(element));
    }

    public ArrayElement set(int index, Number element) {
        return this.set(index, number(element));
    }

    public ArrayElement set(int index, String element) {
        return this.set(index, string(element));
    }

    public ArrayElement insert(int index, Element element) {
        if (element == null) throw new IllegalArgumentException("Element cannot be null!");
        return this.copy(list -> list.add(index, element));
    }

    public ArrayElement insert(int index, boolean element) {
        return this.insert(index, bool(element));
    }

    public ArrayElement insert(int index, Number element) {
        return this.insert(index, number(element));
    }

    public ArrayElement insert(int index, String element) {
        return this.insert(index, string(element));
    }

    public ArrayElement remove(int index) {
        return this.copy(list -> list.remove(index));
    }

    public ArrayElement removeValue(Element element) {
        return this.copy(list -> list.remove(element));
    }

    public ArrayElement removeValue(boolean element) {
        return this.removeValue(bool(element));
    }

    public ArrayElement removeValue(Number element) {
        return this.removeValue(number(element));
    }

    public ArrayElement removeValue(String element) {
        return this.removeValue(string(element));
    }

    public ArrayElement clear() {
        return this.copy(List::clear);
    }

    public ArrayElement reverse() {
        return this.copy(Collections::reverse);
    }

    public ArrayElement copy(Consumer<List<Element>> mutator) {
        if (mutator == null) throw new IllegalArgumentException("Mutator cannot be null!");
        var list = new ArrayList<>(this.elements);
        mutator.accept(list);
        return new ArrayElement(list);
    }
}
