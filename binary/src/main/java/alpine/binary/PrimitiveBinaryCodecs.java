package alpine.binary;

import io.netty.buffer.ByteBuf;

import static alpine.binary.BinaryCodec.*;

/**
 * Built-in binary codecs for Java's primitive types.
 * @see BinaryCodec
 * @author mudkip
 */
interface PrimitiveBinaryCodecs {
    /**
     * A binary codec which serializes a boolean as a {@code 0} or {@code 1} value.
     * If the value is {@code 0}, then {@code false} is returned. Otherwise, {@code true} is returned.
     */
    BinaryCodec<Boolean> BOOLEAN = of(ByteBuf::readBoolean, ByteBuf::writeBoolean);

    /**
     * A binary codec which serializes a signed 8-bit integer.
     */
    BinaryCodec<Byte> BYTE = of(ByteBuf::readByte, (buffer, value) -> buffer.writeByte(value));

    /**
     * A binary codec which serializes a two byte UTF-16 character.
     */
    BinaryCodec<Character> CHARACTER = of(ByteBuf::readChar, (buffer, value) -> buffer.writeChar(Character.getNumericValue(value)));

    /**
     * A binary codec which serializes a signed 16-bit integer.
     */
    BinaryCodec<Short> SHORT = of(ByteBuf::readShort, (buffer, value) -> buffer.writeShort(value));

    /**
     * A binary codec which serializes a signed 32-bit integer.
     */
    BinaryCodec<Integer> INTEGER = of(ByteBuf::readInt, ByteBuf::writeInt);

    /**
     * A binary codec which serializes a signed 64-bit integer.
     */
    BinaryCodec<Long> LONG = of(ByteBuf::readLong, ByteBuf::writeLong);

    /**
     * A binary codec which serializes a signed 32-bit floating-point decimal.
     */
    BinaryCodec<Float> FLOAT = of(ByteBuf::readFloat, ByteBuf::writeFloat);

    /**
     * A binary codec which serializes a signed 64-bit floating-point decimal.
     */
    BinaryCodec<Double> DOUBLE = of(ByteBuf::readDouble, ByteBuf::writeDouble);
}
