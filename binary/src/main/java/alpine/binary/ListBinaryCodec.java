package alpine.binary;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * A binary codec which serializes a sequence of values.
 * @param parent The binary codec to serialize the values with.
 * @param <T> The element type.
 * @see BinaryCodec
 * @author mudkip
 */
@ApiStatus.Internal
record ListBinaryCodec<T>(BinaryCodec<T> parent) implements BinaryCodec<List<T>> {
    @Override
    public List<T> read(ByteBuf buffer) {
        var size = VARINT.read(buffer);
        var list = new ArrayList<T>(size);

        for (var index = 0; index < size; index++) {
            list.add(this.parent.read(buffer));
        }

        return list;
    }

    @Override
    public void write(ByteBuf buffer, List<T> value) {
        VARINT.write(buffer, value.size());
        value.forEach(it -> this.parent.write(buffer, it));
    }
}
