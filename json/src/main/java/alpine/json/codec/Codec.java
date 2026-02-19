package alpine.json.codec;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;

public interface Codec<T> extends PrimitiveCodecs, StandardCodecs, PrimitiveArrayCodecs {
    <R> T decode(Transcoder<R> transcoder, R value);

    <R> R encode(Transcoder<R> transcoder, T value);

    default <U> Codec<U> map(Function<T, U> to, Function<U, T> from) {
        return new Codec<>() {
            @Override
            public <R> U decode(Transcoder<R> transcoder, R value) {
                return to.apply(Codec.this.decode(transcoder, value));
            }

            @Override
            public <R> R encode(Transcoder<R> transcoder, U value) {
                return Codec.this.encode(transcoder, from.apply(value));
            }
        };
    }

    default Codec<@Nullable T> nullable() {
        return new Codec<>() {
            @Override
            public <R> @Nullable T decode(Transcoder<R> transcoder, R value) {
                return transcoder.isNull(value) ? null : Codec.this.decode(transcoder, value);
            }

            @Override
            public <R> R encode(Transcoder<R> transcoder, @Nullable T value) {
                return value == null ? transcoder.encodeNull() : Codec.this.encode(transcoder, value);
            }
        };
    }

    default Codec<Optional<T>> optional() {
        return new Codec<>() {
            @Override
            public <R> Optional<T> decode(Transcoder<R> transcoder, R value) {
                return transcoder.isNull(value) ? Optional.empty() : Optional.of(Codec.this.decode(transcoder, value));
            }

            @Override
            public <R> R encode(Transcoder<R> transcoder, Optional<T> value) {
                return value.isEmpty() ? transcoder.encodeNull() : Codec.this.encode(transcoder, value.get());
            }
        };
    }

    default Codec<List<T>> list() {
        return new Codec<>() {
            @Override
            public <R> List<T> decode(Transcoder<R> transcoder, R value) {
                return transcoder.decodeArray(value).stream()
                        .map(element -> Codec.this.decode(transcoder, element))
                        .toList();
            }

            @Override
            public <R> R encode(Transcoder<R> transcoder, List<T> value) {
                return transcoder.encodeArray(value.stream()
                        .map(element -> Codec.this.encode(transcoder, element))
                        .toList());
            }
        };
    }

    default Codec<T[]> array(IntFunction<T[]> constructor) {
        return new Codec<>() {
            @Override
            public <R> T[] decode(Transcoder<R> transcoder, R value) {
                var elements = transcoder.decodeArray(value);
                var result = constructor.apply(elements.size());

                for (var index = 0; index < elements.size(); index++) {
                    result[index] = Codec.this.decode(transcoder, elements.get(index));
                }

                return result;
            }

            @Override
            public <R> R encode(Transcoder<R> transcoder, T[] array) {
                var elements = new ArrayList<R>(array.length);

                for (var item : array) {
                    elements.add(Codec.this.encode(transcoder, item));
                }

                return transcoder.encodeArray(elements);
            }
        };
    }

    static <V> Codec<Map<String, V>> map(Codec<V> valueCodec) {
        return new Codec<>() {
            @Override
            public <R> Map<String, V> decode(Transcoder<R> transcoder, R value) {
                var decoded = new LinkedHashMap<String, V>();
                transcoder.decodeObject(value).forEach((key, entry) -> decoded.put(key, valueCodec.decode(transcoder, entry)));
                return decoded;
            }

            @Override
            public <R> R encode(Transcoder<R> transcoder, Map<String, V> value) {
                var encoded = new LinkedHashMap<String, R>();
                value.forEach((key, entry) -> encoded.put(key, valueCodec.encode(transcoder, entry)));
                return transcoder.encodeObject(encoded);
            }
        };
    }

    static <E extends Enum<E>> Codec<E> name(Class<E> enumClass) {
        return new Codec<>() {
            @Override
            public <R> E decode(Transcoder<R> transcoder, R value) {
                var name = transcoder.decodeString(value);

                for (var constant : enumClass.getEnumConstants()) {
                    if (constant.name().toLowerCase().equals(name)) {
                        return constant;
                    }
                }

                throw new DecodingException("Unknown enum constant: " + name);
            }

            @Override
            public <R> R encode(Transcoder<R> transcoder, E value) {
                return transcoder.encodeString(value.name().toLowerCase());
            }
        };
    }

    static <T> CodecBuilder._0<T> builder() {
        return new CodecBuilder._0<>();
    }
}
