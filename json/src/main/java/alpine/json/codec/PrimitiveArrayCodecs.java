package alpine.json.codec;

interface PrimitiveArrayCodecs {
    Codec<boolean[]> BOOLEAN_ARRAY = new Codec<>() {
        @Override
        public <R> boolean[] decode(Transcoder<R> transcoder, R value) {
            var elements = transcoder.decodeArray(value);
            var result = new boolean[elements.size()];

            for (var index = 0; index < elements.size(); index++) {
                result[index] = transcoder.decodeBoolean(elements.get(index));
            }

            return result;
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, boolean[] array) {
            var elements = new java.util.ArrayList<R>(array.length);

            for (var item : array) {
                elements.add(transcoder.encodeBoolean(item));
            }

            return transcoder.encodeArray(elements);
        }
    };

    Codec<byte[]> BYTE_ARRAY = new Codec<>() {
        @Override
        public <R> byte[] decode(Transcoder<R> transcoder, R value) {
            var elements = transcoder.decodeArray(value);
            var result = new byte[elements.size()];

            for (var index = 0; index < elements.size(); index++) {
                result[index] = (byte) transcoder.decodeNumber(elements.get(index));
            }

            return result;
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, byte[] array) {
            var elements = new java.util.ArrayList<R>(array.length);

            for (var item : array) {
                elements.add(transcoder.encodeNumber(item));
            }

            return transcoder.encodeArray(elements);
        }
    };

    Codec<char[]> CHARACTER_ARRAY = new Codec<>() {
        @Override
        public <R> char[] decode(Transcoder<R> transcoder, R value) {
            var elements = transcoder.decodeArray(value);
            var result = new char[elements.size()];

            for (var index = 0; index < elements.size(); index++) {
                var str = transcoder.decodeString(elements.get(index));
                if (str.length() != 1) throw new DecodingException("Expected a single character string!");
                result[index] = str.charAt(0);
            }

            return result;
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, char[] array) {
            var elements = new java.util.ArrayList<R>(array.length);

            for (var item : array) {
                elements.add(transcoder.encodeString(String.valueOf(item)));
            }

            return transcoder.encodeArray(elements);
        }
    };

    Codec<short[]> SHORT_ARRAY = new Codec<>() {
        @Override
        public <R> short[] decode(Transcoder<R> transcoder, R value) {
            var elements = transcoder.decodeArray(value);
            var result = new short[elements.size()];

            for (var index = 0; index < elements.size(); index++) {
                result[index] = (short) transcoder.decodeNumber(elements.get(index));
            }

            return result;
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, short[] array) {
            var elements = new java.util.ArrayList<R>(array.length);

            for (var item : array) {
                elements.add(transcoder.encodeNumber(item));
            }

            return transcoder.encodeArray(elements);
        }
    };

    Codec<int[]> INTEGER_ARRAY = new Codec<>() {
        @Override
        public <R> int[] decode(Transcoder<R> transcoder, R value) {
            var elements = transcoder.decodeArray(value);
            var result = new int[elements.size()];

            for (var index = 0; index < elements.size(); index++) {
                result[index] = (int) transcoder.decodeNumber(elements.get(index));
            }

            return result;
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, int[] array) {
            var elements = new java.util.ArrayList<R>(array.length);

            for (var item : array) {
                elements.add(transcoder.encodeNumber(item));
            }

            return transcoder.encodeArray(elements);
        }
    };

    Codec<long[]> LONG_ARRAY = new Codec<>() {
        @Override
        public <R> long[] decode(Transcoder<R> transcoder, R value) {
            var elements = transcoder.decodeArray(value);
            var result = new long[elements.size()];

            for (var index = 0; index < elements.size(); index++) {
                result[index] = (long) transcoder.decodeNumber(elements.get(index));
            }

            return result;
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, long[] array) {
            var elements = new java.util.ArrayList<R>(array.length);

            for (var item : array) {
                elements.add(transcoder.encodeNumber(item));
            }

            return transcoder.encodeArray(elements);
        }
    };

    Codec<float[]> FLOAT_ARRAY = new Codec<>() {
        @Override
        public <R> float[] decode(Transcoder<R> transcoder, R value) {
            var elements = transcoder.decodeArray(value);
            var result = new float[elements.size()];

            for (var index = 0; index < elements.size(); index++) {
                result[index] = (float) transcoder.decodeNumber(elements.get(index));
            }

            return result;
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, float[] array) {
            var elements = new java.util.ArrayList<R>(array.length);

            for (var item : array) {
                elements.add(transcoder.encodeNumber(item));
            }

            return transcoder.encodeArray(elements);
        }
    };

    Codec<double[]> DOUBLE_ARRAY = new Codec<>() {
        @Override
        public <R> double[] decode(Transcoder<R> transcoder, R value) {
            var elements = transcoder.decodeArray(value);
            var result = new double[elements.size()];

            for (var index = 0; index < elements.size(); index++) {
                result[index] = transcoder.decodeNumber(elements.get(index));
            }

            return result;
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, double[] array) {
            var elements = new java.util.ArrayList<R>(array.length);

            for (var item : array) {
                elements.add(transcoder.encodeNumber(item));
            }

            return transcoder.encodeArray(elements);
        }
    };
}
