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

    Codec<Double> NUMBER = new Codec<>() {
        @Override
        public <R> Double decode(Transcoder<R> transcoder, R value) {
            return transcoder.decodeNumber(value);
        }

        @Override
        public <R> R encode(Transcoder<R> transcoder, Double value) {
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
