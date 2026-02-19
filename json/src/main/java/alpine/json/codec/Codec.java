package alpine.json.codec;

public interface Codec<T> extends PrimitiveCodecs {
    <R> T decode(Transcoder<R> transcoder, R value);

    <R> R encode(Transcoder<R> transcoder, T value);

    static <T> CodecBuilder._0<T> builder() {
        return new CodecBuilder._0<>();
    }
}
