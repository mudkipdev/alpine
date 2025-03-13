package alpine.binary;

import io.netty.buffer.ByteBuf;

/**
 * Built-in binary codecs for various primitive arrays.
 * @author mudkip
 */
interface ArrayBinaryCodecs {
    BinaryCodec<boolean[]> BOOLEAN_ARRAY = new BinaryCodec<>() {
        @Override
        public boolean[] read(ByteBuf buffer) {
            var length = VARINT.read(buffer);
            var data = new boolean[length];

            for (var index = 0; index < length; index++) {
                data[index] = BOOLEAN.read(buffer);
            }

            return data;
        }

        @Override
        public void write(ByteBuf buffer, boolean[] array) {
            VARINT.write(buffer, array.length);

            for (var value : array) {
                BOOLEAN.write(buffer, value);
            }
        }
    };

    BinaryCodec<char[]> CHARACTER_ARRAY = new BinaryCodec<>() {
        @Override
        public char[] read(ByteBuf buffer) {
            var length = VARINT.read(buffer);
            var data = new char[length];

            for (var index = 0; index < length; index++) {
                data[index] = CHARACTER.read(buffer);
            }

            return data;
        }

        @Override
        public void write(ByteBuf buffer, char[] array) {
            VARINT.write(buffer, array.length);

            for (var value : array) {
                CHARACTER.write(buffer, value);
            }
        }
    };

    BinaryCodec<byte[]> BYTE_ARRAY = new BinaryCodec<>() {
        @Override
        public byte[] read(ByteBuf buffer) {
            var length = VARINT.read(buffer);
            var data = new byte[length];

            for (var index = 0; index < length; index++) {
                data[index] = BYTE.read(buffer);
            }

            return data;
        }

        @Override
        public void write(ByteBuf buffer, byte[] array) {
            VARINT.write(buffer, array.length);
            buffer.writeBytes(array);
        }
    };

    BinaryCodec<short[]> SHORT_ARRAY = new BinaryCodec<>() {
        @Override
        public short[] read(ByteBuf buffer) {
            var length = VARINT.read(buffer);
            var data = new short[length];

            for (var index = 0; index < length; index++) {
                data[index] = SHORT.read(buffer);
            }

            return data;
        }

        @Override
        public void write(ByteBuf buffer, short[] array) {
            VARINT.write(buffer, array.length);

            for (var value : array) {
                SHORT.write(buffer, value);
            }
        }
    };

    BinaryCodec<int[]> INTEGER_ARRAY = new BinaryCodec<>() {
        @Override
        public int[] read(ByteBuf buffer) {
            var length = VARINT.read(buffer);
            var data = new int[length];

            for (var index = 0; index < length; index++) {
                data[index] = INTEGER.read(buffer);
            }

            return data;
        }

        @Override
        public void write(ByteBuf buffer, int[] array) {
            VARINT.write(buffer, array.length);

            for (var value : array) {
                INTEGER.write(buffer, value);
            }
        }
    };

    BinaryCodec<long[]> LONG_ARRAY = new BinaryCodec<>() {
        @Override
        public long[] read(ByteBuf buffer) {
            var length = VARINT.read(buffer);
            var data = new long[length];

            for (var index = 0; index < length; index++) {
                data[index] = LONG.read(buffer);
            }

            return data;
        }

        @Override
        public void write(ByteBuf buffer, long[] array) {
            VARINT.write(buffer, array.length);

            for (var value : array) {
                LONG.write(buffer, value);
            }
        }
    };

    BinaryCodec<float[]> FLOAT_ARRAY = new BinaryCodec<>() {
        @Override
        public float[] read(ByteBuf buffer) {
            var length = VARINT.read(buffer);
            var data = new float[length];

            for (var index = 0; index < length; index++) {
                data[index] = FLOAT.read(buffer);
            }

            return data;
        }

        @Override
        public void write(ByteBuf buffer, float[] array) {
            VARINT.write(buffer, array.length);

            for (var value : array) {
                FLOAT.write(buffer, value);
            }
        }
    };

    BinaryCodec<double[]> DOUBLE_ARRAY = new BinaryCodec<>() {
        @Override
        public double[] read(ByteBuf buffer) {
            var length = VARINT.read(buffer);
            var data = new double[length];

            for (var index = 0; index < length; index++) {
                data[index] = DOUBLE.read(buffer);
            }

            return data;
        }

        @Override
        public void write(ByteBuf buffer, double[] array) {
            VARINT.write(buffer, array.length);

            for (var value : array) {
                DOUBLE.write(buffer, value);
            }
        }
    };
}
