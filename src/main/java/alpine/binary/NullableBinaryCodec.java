package alpine.binary;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * A binary codec which may or may not have a value present.
 * The value will be read as {@code null} if it is not present.
 * @param parent The codec to serialize the value with.
 * @param <T> The value type.
 * @see BinaryCodec
 * @see OptionalBinaryCodec
 * @author mudkip
 */
@ApiStatus.Internal
record NullableBinaryCodec<T>(BinaryCodec<T> parent) implements BinaryCodec<T> {
    @Override
    public @Nullable T read(ByteBuf buffer) {
        if (BOOLEAN.read(buffer)) {
            return this.parent.read(buffer);
        } else {
            return null;
        }
    }

    @Override
    public void write(ByteBuf buffer, @Nullable T value) {
        BOOLEAN.write(buffer, value != null);

        if (value != null) {
            this.parent.write(buffer, value);
        }
    }
}

