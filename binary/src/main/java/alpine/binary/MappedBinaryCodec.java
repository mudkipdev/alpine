package alpine.binary;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Function;

/**
 * A binary codec which converts one type to another.
 * @param parent The binary codec to serialize the value with.
 * @param to The function to transform the original value to a new type.
 * @param from The function to transform the new type to the original type.
 * @param <T> The original value type.
 * @param <U> The new value type.
 * @author mudkip
 */
@ApiStatus.Internal
record MappedBinaryCodec<T, U>(BinaryCodec<T> parent, Function<T, U> to, Function<U, T> from) implements BinaryCodec<U> {
    @Override
    public U read(ByteBuf buffer) {
        return this.to.apply(this.parent.read(buffer));
    }

    @Override
    public void write(ByteBuf buffer, U value) {
        this.parent.write(buffer, this.from.apply(value));
    }
}
