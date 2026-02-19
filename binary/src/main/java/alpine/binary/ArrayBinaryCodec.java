package alpine.binary;

import io.netty.buffer.ByteBuf;

import java.util.function.IntFunction;

/**
 * A binary codec which serializes a sequence of values as an array.
 * @param parent The binary codec to serialize the values with.
 * @param constructor A function to allocate the correctly-typed array given a length.
 * @param <T> The element type.
 * @author mudkip
 */
record ArrayBinaryCodec<T>(BinaryCodec<T> parent, IntFunction<T[]> constructor) implements BinaryCodec<T[]> {
    @Override
    public T[] read(ByteBuf buffer) {
        var length = VARINT.read(buffer);
        var data = this.constructor.apply(length);

        for (var index = 0; index < length; index++) {
            data[index] = this.parent.read(buffer);
        }

        return data;
    }

    @Override
    public void write(ByteBuf buffer, T[] array) {
        VARINT.write(buffer, array.length);

        for (var value : array) {
            this.parent.write(buffer, value);
        }
    }
}
