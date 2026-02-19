package alpine.json.codec;

import alpine.json.Element;

import java.util.List;
import java.util.Map;

public interface Transcoder<T> {
    Transcoder<Element> JSON = new JsonTranscoder();

    boolean isNull(T value);

    T encodeNull();

    boolean decodeBoolean(T value);

    T encodeBoolean(boolean value);

    double decodeNumber(T value);

    T encodeNumber(double value);

    String decodeString(T value);

    T encodeString(String value);

    List<T> decodeArray(T value);

    T encodeArray(List<T> elements);

    Map<String, T> decodeObject(T value);

    T decodeObjectField(T object, String key);

    T encodeObject(Map<String, T> fields);
}
