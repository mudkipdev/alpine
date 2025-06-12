package alpine.json;

import java.util.function.Function;

/**
 * Represents something that can encode and decode a value to a JSON element.
 * @param <T> The value type.
 * @param <E> The type of the element the codec will be writing to.
 * @author mudkip
 */
public interface JsonCodec<T, E extends Element> {
    /**
     * A JSON codec which serializes a boolean as a {@code true} or {@code false} value.
     */
    JsonCodec<Boolean, BooleanElement> BOOLEAN = of(BooleanElement::value, Element::bool);

    /**
     * A JSON codec which serializes an integer or a fractional number.
     */
    JsonCodec<Number, NumberElement> NUMBER = of(NumberElement::value, Element::number);

    /**
     * A JSON codec which serializes a sequence of characters.
     */
    JsonCodec<String, StringElement> STRING = of(StringElement::value, Element::string);

    /**
     * Decodes the value from the element.
     * @param element The element
     * @return The decoded value.
     */
    T decode(E element);

    /**
     * Encodes a value to an element.
     * @param value The
     * @return The JSON element.
     */
    E encode(T value);

    default <U> JsonCodec<U, E> map(Function<T, U> to, Function<U, T> from) {
        return new JsonCodec<>() {
            @Override
            public U decode(E element) {
                return to.apply(JsonCodec.this.decode(element));
            }

            @Override
            public E encode(U value) {
                return JsonCodec.this.encode(from.apply(value));
            }
        };
    }

    static <T, E extends Element> JsonCodec<T, E> of(Function<E, T> decoder, Function<T, E> encoder) {
        return new JsonCodec<>() {
            @Override
            public T decode(E element) {
                return decoder.apply(element);
            }

            @Override
            public E encode(T value) {
                return encoder.apply(value);
            }
        };
    }

    static <T, E extends Element> JsonCodec<T, E> decodeOnly(Function<E, T> decoder) {
        return of(decoder, value -> {
            throw new UnsupportedOperationException("This JSON codec does not support encoding!");
        });
    }

    static <T, E extends Element> JsonCodec<T, E> encodeOnly(Function<T, E> encoder) {
        return of(value -> {
            throw new UnsupportedOperationException("This JSON codec does not support encoding!");
        }, encoder);
    }

    static <T extends Enum<T>> JsonCodec<T, StringElement> name(Class<T> clazz) {
        return STRING.map(name -> Enum.valueOf(clazz, name), Enum::name);
    }
}
