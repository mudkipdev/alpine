package alpine.json;

import alpine.json.Element;

import java.util.function.Function;

public interface JsonCodec<T, E extends Element> {
    T decode(E element);

    E encode(T value);

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
}
