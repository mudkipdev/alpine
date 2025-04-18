package alpine.json;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * A JSON element which in itself stores an ordered sequence of elements.
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
        return Json.write(this, Json.Formatting.PRETTY);
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

    public ArrayElement append(Element element) {
        if (element == null) throw new IllegalArgumentException("Element cannot be null!");
        return this.copy(list -> list.add(element));
    }

    public ArrayElement append(boolean element) {
        return this.append(Element.bool(element));
    }

    public ArrayElement append(Number element) {
        return this.append(Element.number(element));
    }

    public ArrayElement append(String element) {
        return this.append(Element.string(element));
    }

    public ArrayElement insert(int index, Element element) {
        if (element == null) throw new IllegalArgumentException("Element cannot be null!");
        return this.copy(list -> list.add(index, element));
    }

    public ArrayElement insert(int index, boolean element) {
        return this.insert(index, Element.bool(element));
    }

    public ArrayElement insert(int index, Number element) {
        return this.insert(index, Element.number(element));
    }

    public ArrayElement insert(int index, String element) {
        return this.insert(index, Element.string(element));
    }

    public ArrayElement removeAt(int index) {
        return this.copy(list -> list.remove(index));
    }

    public ArrayElement remove(Element element) {
        return this.copy(list -> list.remove(element));
    }

    public ArrayElement remove(boolean element) {
        return this.remove(Element.bool(element));
    }

    public ArrayElement remove(Number element) {
        return this.remove(Element.number(element));
    }

    public ArrayElement remove(String element) {
        return this.remove(Element.string(element));
    }

    public ArrayElement clear() {
        return this.copy(List::clear);
    }

    public ArrayElement copy(Consumer<List<Element>> mutator) {
        if (mutator == null) throw new IllegalArgumentException("Mutator cannot be null!");
        var list = new ArrayList<>(this.elements);
        mutator.accept(list);
        return new ArrayElement(list);
    }
}
