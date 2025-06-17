package alpine.json;

import java.util.*;

/**
 * The base class for a JSON element.
 * <p>
 * A JSON element can be one of the following types:
 * <ul>
 *     <li>{@code null} — represented by {@link NullElement}</li>
 *     <li>{@code boolean} — represented by {@link BooleanElement}</li>
 *     <li>{@code number} — represented by {@link NumberElement}</li>
 *     <li>{@code string} — represented by {@link StringElement}</li>
 *     <li>{@code array} — represented by {@link ArrayElement}</li>
 *     <li>{@code object} — represented by {@link ObjectElement}</li>
 * </ul>
 * @author mudkip
 */
public sealed interface Element permits ArrayElement, BooleanElement, NullElement, NumberElement, ObjectElement, StringElement {
    static NullElement nil() {
        return NullElement.INSTANCE;
    }

    static BooleanElement bool(boolean value) {
        return new BooleanElement(value);
    }

    static NumberElement number(double value) {
        if (Double.isNaN(value) && Double.isInfinite(value)) {
            throw new IllegalArgumentException("NaN and infinite numbers are not allowed!");
        }

        return new NumberElement(value);
    }

    static NumberElement number(Number value) {
        if (value == null) {
            throw new IllegalArgumentException("Number value cannot be null!");
        }

        return new NumberElement(value.doubleValue());
    }

    static StringElement string(String value) {
        return new StringElement(value);
    }

    static ArrayElement array(List<Element> elements) {
        return new ArrayElement(new ArrayList<>(elements));
    }

    static ArrayElement array() {
        return new ArrayElement();
    }

    static ArrayElement array(Element... elements) {
        return array(List.of(elements));
    }

    static ArrayElement array(Boolean... elements) {
        return array(Arrays.stream(elements)
                .map(Element::bool)
                .map(Element.class::cast)
                .toList());
    }

    static ArrayElement array(String... elements) {
        return array(Arrays.stream(elements)
                .map(Element::string)
                .map(Element.class::cast)
                .toList());
    }

    static ArrayElement array(Number... elements) {
        return array(Arrays.stream(elements)
                .map(Element::number)
                .map(Element.class::cast)
                .toList());
    }

    static ObjectElement object() {
        return new ObjectElement();
    }

    static ObjectElement object(Map<String, Element> map) {
        return new ObjectElement(new LinkedHashMap<>(map));
    }
}
