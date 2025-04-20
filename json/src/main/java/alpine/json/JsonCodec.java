package alpine.json;

import java.util.function.Function;

public interface JsonCodec<T, E extends Element> {
    JsonCodec<Boolean, BooleanElement> BOOLEAN = of(BooleanElement::value, Element::bool);
    JsonCodec<Number, NumberElement> NUMBER = of(NumberElement::value, Element::number);
    JsonCodec<String, StringElement> STRING = of(StringElement::value, Element::string);

    T decode(E element);

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

    static <T, E extends Element> JsonCodec<T, E> decode(Function<E, T> decoder) {
        return of(decoder, value -> {
            throw new UnsupportedOperationException("This JSON codec does not support encoding!");
        });
    }

    static <T, E extends Element> JsonCodec<T, E> encode(Function<T, E> encoder) {
        return of(value -> {
            throw new UnsupportedOperationException("This JSON codec does not support encoding!");
        }, encoder);
    }

    static <T extends Enum<T>> JsonCodec<T, StringElement> name(Class<T> clazz) {
        return STRING.map(name -> Enum.valueOf(clazz, name), Enum::name);
    }
}
