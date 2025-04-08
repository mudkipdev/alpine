package alpine.binary;

import io.netty.buffer.Unpooled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

final class CodecTest {
    static Stream<Arguments> arguments() {
        return Stream.of(
                // Boolean
                Arguments.of("Boolean", BinaryCodec.BOOLEAN, false, new byte[] {0}),
                Arguments.of("Boolean", BinaryCodec.BOOLEAN, true, new byte[] {1}),

                // String
                Arguments.of("String", BinaryCodec.STRING, "ABC", new byte[] {3, 65, 66, 67}),

                // Either
                Arguments.of(
                        "Either",
                        BinaryCodec.either(BinaryCodec.INTEGER, BinaryCodec.STRING),
                        Either.right("A"),
                        new byte[] {1, 1, 65}),

                // Optional
                Arguments.of("Optional", BinaryCodec.BOOLEAN.optional(), Optional.empty(), new byte[] {0}),
                Arguments.of("Optional", BinaryCodec.INTEGER.optional(), Optional.of(3), new byte[] {1, 0, 0, 0, 3}));
    }

    @ParameterizedTest(name = "{0} (encoding)")
    @MethodSource("arguments")
    <T> void testEncoding(String label, BinaryCodec<T> codec, T value, byte[] data) {
        var buffer = Unpooled.buffer();
        codec.write(buffer, value);
        var array = new byte[buffer.readableBytes()];
        buffer.getBytes(buffer.readerIndex(), array);

        assert Arrays.equals(array, data) : String.format(
                "Expected %s while encoding, got %s.",
                Arrays.toString(data),
                Arrays.toString(array));
    }

    @ParameterizedTest(name = "{0} (decoding)")
    @MethodSource("arguments")
    <T> void testDecoding(String label, BinaryCodec<T> codec, T value, byte[] data) {
        var buffer = Unpooled.buffer();
        buffer.writeBytes(data);
        var actualValue = codec.read(buffer);

        assert Objects.equals(actualValue, value) : String.format(
                "Expected %s while decoding, got %s.", value, actualValue);
    }
}
