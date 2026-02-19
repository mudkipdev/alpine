package alpine.json.codec;

interface PrimitiveCodecs {
    Codec<Boolean> BOOLEAN = new Codec<>() {
        @Override
        public <R> Boolean decode(Transcoder<R> transcoder, R value) {
            return transcoder.decodeBoolean(value);
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, Boolean value) {
            return transcoder.encodeBoolean(value);
        }
    };

    Codec<Byte> BYTE = new Codec<>() {
        @Override
        public <R> Byte decode(Transcoder<R> transcoder, R value) {
            return (byte) transcoder.decodeNumber(value);
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, Byte value) {
            return transcoder.encodeNumber(value);
        }
    };

    Codec<Character> CHARACTER = new Codec<>() {
        @Override
        public <R> Character decode(Transcoder<R> transcoder, R value) {
            var s = transcoder.decodeString(value);
            if (s.length() != 1) throw new DecodingException("Expected a single character string!");
            return s.charAt(0);
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, Character value) {
            return transcoder.encodeString(String.valueOf(value));
        }
    };

    Codec<Short> SHORT = new Codec<>() {
        @Override
        public <R> Short decode(Transcoder<R> transcoder, R value) {
            return (short) transcoder.decodeNumber(value);
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, Short value) {
            return transcoder.encodeNumber(value);
        }
    };

    Codec<Integer> INTEGER = new Codec<>() {
        @Override
        public <R> Integer decode(Transcoder<R> transcoder, R value) {
            return (int) transcoder.decodeNumber(value);
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, Integer value) {
            return transcoder.encodeNumber(value);
        }
    };

    Codec<Long> LONG = new Codec<>() {
        @Override
        public <R> Long decode(Transcoder<R> transcoder, R value) {
            return (long) transcoder.decodeNumber(value);
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, Long value) {
            return transcoder.encodeNumber(value);
        }
    };

    Codec<Float> FLOAT = new Codec<>() {
        @Override
        public <R> Float decode(Transcoder<R> transcoder, R value) {
            return (float) transcoder.decodeNumber(value);
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, Float value) {
            return transcoder.encodeNumber(value);
        }
    };

    Codec<Double> DOUBLE = new Codec<>() {
        @Override
        public <R> Double decode(Transcoder<R> transcoder, R value) {
            return transcoder.decodeNumber(value);
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, Double value) {
            return transcoder.encodeNumber(value);
        }
    };

    Codec<String> STRING = new Codec<>() {
        @Override
        public <R> String decode(Transcoder<R> transcoder, R value) {
            return transcoder.decodeString(value);
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, String value) {
            return transcoder.encodeString(value);
        }
    };
}
