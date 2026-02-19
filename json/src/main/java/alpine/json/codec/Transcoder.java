package alpine.json.codec;

import alpine.json.Element;

import java.util.Map;

public interface Transcoder<T> {
    Transcoder<Element> JSON = new JsonTranscoder();

    T encodeNull();

    boolean decodeBoolean(T value);

    T encodeBoolean(boolean value);

    double decodeNumber(T value);

    T encodeNumber(double value);

    String decodeString(T value);

    T encodeString(String value);

    T decodeObjectField(T object, String key);

    T encodeObject(Map<String, T> fields);
}
