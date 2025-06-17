package alpine.binary;

import io.netty.buffer.ByteBuf;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static alpine.binary.ArrayBinaryCodecs.BYTE_ARRAY;
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

    /**
     * Returns binary codec which serializes an image.
     * @return A binary codec which serializes an image.
     * @see java.awt.image.BufferedImage
     */
    static BinaryCodec<BufferedImage> image(String format) {
        return BYTE_ARRAY.map(
                array -> {
                    try (var stream = new ByteArrayInputStream(array)) {
                        return ImageIO.read(stream);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to read image!", e);
                    }
                },
                image -> {
                    try {
                        var stream = new ByteArrayOutputStream();
                        ImageIO.write(image, format, stream);
                        return stream.toByteArray();
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to write image!", e);
                    }
                });
    }
}
