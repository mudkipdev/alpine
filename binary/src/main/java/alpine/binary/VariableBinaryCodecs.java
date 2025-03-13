package alpine.binary;

import io.netty.buffer.ByteBuf;

/**
 * Implements VarInt and VarLong binary codecs.
 * @author mudkip
 */
interface VariableBinaryCodecs {
    /**
     * <a href="https://en.wikipedia.org/wiki/LEB128">LEB128</a> encoded. Uses between 1 and 5 bytes.
     */
    BinaryCodec<Integer> VARINT = new BinaryCodec<>() {
        private static final int SEGMENT_BITS = 0x7F;
        private static final int CONTINUE_BIT = 0x80;

        @Override
        public Integer read(ByteBuf buffer) {
            var readable = buffer.readableBytes();
            var k = buffer.readByte();

            if ((k & CONTINUE_BIT) != 128) {
                return (int) k;
            }

            var maxRead = Math.min(5, readable);
            var i = k & SEGMENT_BITS;

            for (var j = 1; j < maxRead; j++) {
                k = buffer.readByte();
                i |= (k & SEGMENT_BITS) << j * 7;

                if ((k & CONTINUE_BIT) != 128) {
                    return i;
                }
            }

            return 0;
        }

        @Override
        public void write(ByteBuf buffer, Integer value) {
            while (true) {
                if ((value & 0xFFFFFF80) == 0) {
                    buffer.writeByte(value);
                    return;
                }

                buffer.writeByte(value & SEGMENT_BITS | CONTINUE_BIT);
                value >>>= 7;
            }
        }
    };
}
