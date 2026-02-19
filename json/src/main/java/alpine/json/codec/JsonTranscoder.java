package alpine.json.codec;

import alpine.json.BooleanElement;
import alpine.json.Element;
import alpine.json.NumberElement;
import alpine.json.ObjectElement;
import alpine.json.StringElement;

import java.util.Map;

import static alpine.json.Element.*;

final class JsonTranscoder implements Transcoder<Element> {
    @Override
    public Element encodeNull() {
        return nil();
    }

    @Override
    public boolean decodeBoolean(Element value) {
        if (value instanceof BooleanElement element) {
            return element.value();
        } else {
            throw new DecodingException("Expected a boolean!");
        }
    }

    @Override
    public Element encodeBoolean(boolean value) {
        return bool(value);
    }

    @Override
    public double decodeNumber(Element value) {
        if (value instanceof NumberElement element) {
            return element.value();
        } else {
            throw new DecodingException("Expected a number!");
        }
    }

    @Override
    public Element encodeNumber(double value) {
        return number(value);
    }

    @Override
    public String decodeString(Element value) {
        if (value instanceof StringElement element) {
            return element.value();
        } else {
            throw new DecodingException("Expected a string!");
        }
    }

    @Override
    public Element encodeString(String value) {
        return string(value);
    }

    @Override
    public Element decodeObjectField(Element object, String key) {
        if (object instanceof ObjectElement element) {
            return element.get(key);
        } else {
            throw new DecodingException("Expected an object!");
        }
    }

    @Override
    public Element encodeObject(Map<String, Element> fields) {
        return object(fields);
    }
}
