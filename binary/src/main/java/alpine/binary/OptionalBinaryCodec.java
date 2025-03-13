package alpine.binary;

import io.netty.buffer.ByteBuf;

import java.util.Optional;

/**
 * A binary codec which may or may not have a value present.
 * The value is wrapped with Java's {@link Optional} type to ensure proper handling.
 * @param parent The binary codec to serialize the value with.
 * @param <T> The value type.
 * @see NullableBinaryCodec
 * @author mudkip
 */
record OptionalBinaryCodec<T>(BinaryCodec<T> parent) implements BinaryCodec<Optional<T>> {
    @Override
    public Optional<T> read(ByteBuf buffer) {
        if (BOOLEAN.read(buffer)) {
            return Optional.of(this.parent.read(buffer));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void write(ByteBuf buffer, Optional<T> optional) {
        BOOLEAN.write(buffer, optional.isPresent());
        optional.ifPresent(value -> this.parent.write(buffer, value));
    }
}
