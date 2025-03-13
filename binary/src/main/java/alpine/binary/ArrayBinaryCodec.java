package alpine.binary;

import io.netty.buffer.ByteBuf;

/**
 * A binary codec which serializes a sequence of values as an array.
 * @param parent The binary codec to serialize the values with.
 * @param <T> The element type.
 * @author mudkip
 */
record ArrayBinaryCodec<T>(BinaryCodec<T> parent) implements BinaryCodec<T[]> {
    @SuppressWarnings("unchecked")
    @Override
    public T[] read(ByteBuf buffer) {
        var length = VARINT.read(buffer);
        var data = new Object[length];

        for (var index = 0; index < length; index++) {
            data[index] = this.parent.read(buffer);
        }

        return (T[]) data;
    }

    @Override
    public void write(ByteBuf buffer, T[] array) {
        VARINT.write(buffer, array.length);

        for (var value : array) {
            this.parent.write(buffer, value);
        }
    }
}
