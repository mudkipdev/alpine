package alpine.binary;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static alpine.binary.BinaryCodec.LONG;

/**
 * Built-in binary codecs for Java's standard library types.
 * @author mudkip
 */
interface StandardBinaryCodecs {
    BinaryCodec<String> STRING = new StringBinaryCodec(StandardCharsets.UTF_8);

    /**
     * A binary codec which serializes a <a href="https://en.wikipedia.org/wiki/Universally_unique_identifier">UUID</a> as two signed 64-bit integers.
     * @see java.util.UUID
     */
    BinaryCodec<UUID> UUID = BinaryTemplate.of(
            LONG, java.util.UUID::getMostSignificantBits,
            LONG, java.util.UUID::getLeastSignificantBits,
            UUID::new);
}
