package alpine.binary;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.ApiStatus;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A binary codec which serializes an ordered sequence of key-value pairs.
 * @param keyCodec The binary codec to serialize the keys with.
 * @param valueCodec The binary codec to serialize the values with.
 * @param <K> The key type.
 * @param <V> The value type.
 * @author mudkip
 */
@ApiStatus.Internal
record MapBinaryCodec<K, V>(BinaryCodec<K> keyCodec, BinaryCodec<V> valueCodec) implements BinaryCodec<Map<K, V>> {
    @Override
    public Map<K, V> read(ByteBuf buffer) {
        var size = VARINT.read(buffer);
        var map = new LinkedHashMap<K, V>(size);

        for (var index = 0; index < size; index++) {
            map.put(this.keyCodec.read(buffer), this.valueCodec.read(buffer));
        }

        return map;
    }

    @Override
    public void write(ByteBuf buffer, Map<K, V> value) {
        for (var entry : value.entrySet()) {
            this.keyCodec.write(buffer, entry.getKey());
            this.valueCodec.write(buffer, entry.getValue());
        }
    }
}
