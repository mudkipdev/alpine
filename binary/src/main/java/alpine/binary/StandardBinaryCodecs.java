package alpine.binary;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static alpine.binary.BinaryCodec.*;

/**
 * Built-in binary codecs for Java's standard library types.
 * @author mudkip
 */
interface StandardBinaryCodecs {
    BinaryCodec<String> STRING = new BinaryCodec<>() {
        private static final Charset CHARSET = StandardCharsets.UTF_8;

        @Override
        public String read(ByteBuf buffer) {
            var length = VARINT.read(buffer);
            return String.valueOf(buffer.readCharSequence(length, CHARSET));
        }

        @Override
        public void write(ByteBuf buffer, String value) {
            VARINT.write(buffer, value.length());
            buffer.writeCharSequence(value, CHARSET);
        }
    };

    /**
     * A binary codec which serializes a <a href="https://en.wikipedia.org/wiki/Universally_unique_identifier">UUID</a> as two signed 64-bit integers.
     * @see java.util.UUID
     */
    BinaryCodec<UUID> UUID = BinaryTemplate.of(
            LONG, java.util.UUID::getMostSignificantBits,
            LONG, java.util.UUID::getLeastSignificantBits,
            UUID::new);
}
