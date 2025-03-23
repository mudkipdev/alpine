package alpine.binary;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

/**
 * A binary codec which serializes a sequence of characters.
 * @param charset The encoding to serialize the string with.
 * @author mudkip
 */
record StringBinaryCodec(Charset charset) implements BinaryCodec<String> {
    @Override
    public String read(ByteBuf buffer) {
        var length = BinaryCodec.VARINT.read(buffer);
        return String.valueOf(buffer.readCharSequence(length, this.charset));
    }

    @Override
    public void write(ByteBuf buffer, String value) {
        BinaryCodec.VARINT.write(buffer, value.length());
        buffer.writeCharSequence(value, this.charset);
    }
}
