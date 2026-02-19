package alpine.binary;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * Represents something that can encode and decode a value to a byte buffer.
 * @param <T> The value type.
 * @author mudkip
 */
public interface BinaryCodec<T> extends
        PrimitiveBinaryCodecs,
        StandardBinaryCodecs,
        ArrayBinaryCodecs,
        VariableBinaryCodecs {
    /**
     * Decodes the value from the buffer.
     * @param buffer The binary buffer.
     * @return The value read from the buffer.
     */
    T read(ByteBuf buffer);

    /**
     * Encodes the value to the buffer.
     * @param buffer The binary buffer.
     * @param value The value being written to the buffer.
     */
    void write(ByteBuf buffer, T value);

    /**
     * Maps this codec to a different type using the provided functions {@code to} and {@code from}.
     * @param to The function to transform the original value to a new type.
     * @param from The function to transform the new type to the original type.
     * @return The mapped codec.
     * @param <U> The new value type.
     */
    default <U> BinaryCodec<U> map(Function<T, U> to, Function<U, T> from) {
        return new MappedBinaryCodec<>(this, to, from);
    }

    default BinaryCodec<Optional<T>> optional() {
        return new OptionalBinaryCodec<>(this);
    }

    default BinaryCodec<@Nullable T> nullable() {
        return new NullableBinaryCodec<>(this);
    }

    default BinaryCodec<List<T>> list() {
        return new ListBinaryCodec<>(this);
    }

    default BinaryCodec<T[]> array(IntFunction<T[]> constructor) {
        return new ArrayBinaryCodec<>(this, constructor);
    }

    /**
     * Creates a codec based on the {@code reader} and {@code writer} functions.
     * @param reader The function to read the value from the buffer.
     * @param writer The function to write the value to the buffer.
     * @return The newly created codec.
     * @param <T> The value type.
     */
    static <T> BinaryCodec<T> of(Function<ByteBuf, T> reader, BiConsumer<ByteBuf, T> writer) {
        return new BinaryCodec<>() {
            @Override
            public T read(ByteBuf buffer) {
                return reader.apply(buffer);
            }

            @Override
            public void write(ByteBuf buffer, T value) {
                writer.accept(buffer, value);
            }
        };
    }

    static <T> BinaryCodec<T> unsupported() {
        return of(buffer -> {
            throw new UnsupportedOperationException("This binary codec is not implemented!");
        }, (buffer, value) -> {
            throw new UnsupportedOperationException("This binary codec is not implemented!");
        });
    }

    static <T> BinaryCodec<T> decodeOnly(Function<ByteBuf, T> reader) {
        return of(reader, (buffer, value) -> {
            throw new UnsupportedOperationException("This binary codec does not support encoding!");
        });
    }

    static <T> BinaryCodec<T> encodeOnly(BiConsumer<ByteBuf, T> writer) {
        return of(buffer -> {
            throw new UnsupportedOperationException("This binary codec does not support decoding!");
        }, writer);
    }

    static <T> BinaryCodec<T> unit(Supplier<T> supplier) {
        return of(buffer -> supplier.get(), (buffer, value) -> {});
    }

    static <L, R> BinaryCodec<Either<L, R>> either(BinaryCodec<L> leftCodec, BinaryCodec<R> rightCodec) {
        return new EitherBinaryCodec<>(leftCodec, rightCodec);
    }

    static <K, V> BinaryCodec<Map<K, V>> map(BinaryCodec<K> keyCodec, BinaryCodec<V> valueCodec) {
        return new MapBinaryCodec<>(keyCodec, valueCodec);
    }

    static <E extends Enum<E>> BinaryCodec<E> ordinal(Class<E> enumClass) {
        return VARINT.map(ordinal -> enumClass.getEnumConstants()[ordinal], Enum::ordinal);
    }

    static BinaryCodec<String> string(Charset charset) {
        return new StringBinaryCodec(charset);
    }
}
