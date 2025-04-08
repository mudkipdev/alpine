package alpine.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static alpine.json.Element.*;
import static alpine.json.Json.*;
import static java.lang.Character.isDigit;

final class JsonReader {
    private String input;
    private int position;

    Element read(String input) throws ParsingException {
        this.input = input.trim();
        this.position = 0;
        this.skipWhitespace();
        return this.readElement();
    }

    private Element readElement() throws ParsingException {
        var character = this.peek();

        if (character == '"') {
            return this.parseString();
        } else if (isDigit(character) || character == '-') {
            return this.parseNumber();
        } else if (character == TRUE.charAt(0) || character == FALSE.charAt(0)) {
            return this.parseBoolean();
        } else if (character == NULL.charAt(0)) {
            return this.parseNull();
        } else if (character == BEGIN_ARRAY) {
            return this.parseArray();
        } else if (character == BEGIN_OBJECT) {
            return this.parseObject();
        } else {
            throw new ParsingException(this.input, "Unexpected character \"" + character + "\"!", this.position);
        }
    }

    private NullElement parseNull() throws ParsingException {
        if (this.input.startsWith(NULL, this.position)) {
            this.position += NULL.length();
            return nil();
        }

        throw new ParsingException(this.input, "Failed to parse null!", this.position);
    }

    private BooleanElement parseBoolean() throws ParsingException {
        if (this.input.startsWith("true", this.position)) {
            this.position += TRUE.length();
            return bool(true);
        } else if (this.input.startsWith("false", this.position)) {
            this.position += FALSE.length();
            return bool(false);
        }

        throw new ParsingException(this.input, "Failed to parse boolean!", this.position);
    }

    private NumberElement parseNumber() throws ParsingException {
        var start = this.position;
        var length = this.input.length();

        // negative sign
        if (this.peek() == '-') {
            this.position++;
        }

        // integer part
        if (this.peek() == '0') {
            this.position++;
        } else if (this.peek() >= '1' && this.peek() <= '9') {
            while (this.position < length && isDigit(this.input.charAt(this.position))) {
                this.position++;
            }
        } else {
            throw new ParsingException(this.input, "Expected a digit after the minus sign!", this.position);
        }

        // fraction part
        if (this.position < length && this.input.charAt(this.position) == '.') {
            this.position++;
            if (this.position >= length || !isDigit(this.input.charAt(this.position))) {
                throw new ParsingException(this.input, "Expected digit(s) after the decimal point!", this.position);
            }

            while (this.position < length && isDigit(this.input.charAt(this.position))) {
                this.position++;
            }
        }

        // exponent part
        if (this.position < length && this.isExponent(this.input.charAt(this.position))) {
            this.position++;
            if (this.position < length && (this.input.charAt(this.position) == '+' || this.input.charAt(this.position) == '-')) {
                this.position++;
            }

            if (this.position >= length || !isDigit(this.input.charAt(this.position))) {
                throw new ParsingException(this.input, "Expected digit(s) in exponent!", this.position);
            }

            while (this.position < length && isDigit(this.input.charAt(this.position))) {
                this.position++;
            }
        }

        if (
                this.position < length
                && !isWhitespace(this.input.charAt(this.position))
                && ",}]".indexOf(this.input.charAt(this.position)) < 0) {
            throw new ParsingException(this.input, "Invalid character after number!", this.position);
        }

        var text = this.input.substring(start, this.position);

        try {
            return number(Double.parseDouble(text));
        } catch (NumberFormatException exception) {
            throw new ParsingException(this.input, "Invalid number \"" + text + "\"!", start);
        }
    }


    private StringElement parseString() throws ParsingException {
        var builder = new StringBuilder();
        this.expect(QUOTE);

        while (true) {
            if (this.position >= this.input.length()) {
                throw new ParsingException(this.input, "Unterminated string!", this.position);
            }

            var character = this.input.charAt(this.position++);

            if (character == QUOTE) {
                break;
            }

            if (character == BACKSLASH) {
                if (this.position >= this.input.length()) {
                    throw new ParsingException(this.input, "Unterminated escape sequence!", this.position);
                }

                var escapeCharacter = this.input.charAt(this.position++);

                if (escapeCharacter == 'u') {
                    if (this.position + 4 > this.input.length()) {
                        throw new ParsingException(this.input, "Invalid unicode escape", this.position);
                    }

                    var hex = this.input.substring(this.position, this.position + 4);

                    try {
                        var codePoint = Integer.parseInt(hex, 16);
                        builder.append((char) codePoint);
                    } catch (NumberFormatException e) {
                        throw new ParsingException(this.input, "Invalid unicode escape: \\u" + hex, this.position);
                    }

                    this.position += 4;
                } else {
                    var decoded = ESCAPE_TO_CHARACTER.get(escapeCharacter);

                    if (decoded == null) {
                        throw new ParsingException(this.input, "Invalid escape character: \\" + escapeCharacter, this.position);
                    }

                    builder.append(decoded);
                }

            } else if (Character.isISOControl(character)) {
                throw new ParsingException(this.input, "Unescaped control character in string!", this.position);
            } else {
                builder.append(character);
            }
        }

        return string(builder.toString());
    }


    private ArrayElement parseArray() throws ParsingException {
        var elements = new ArrayList<Element>();
        this.expect(BEGIN_ARRAY);
        this.skipWhitespace();

        if (this.peek() == END_ARRAY) {
            this.position++;
            return array();
        }

        while (true) {
            this.skipWhitespace();
            var element = this.readElement();
            elements.add(element);
            this.skipWhitespace();

            if (this.peek() == END_ARRAY) {
                this.position++;
                break;
            }

            this.expect(COMMA);
        }

        return array(elements);
    }

    private ObjectElement parseObject() throws ParsingException {
        var map = new LinkedHashMap<String, Element>();
        this.expect(BEGIN_OBJECT);
        this.skipWhitespace();

        if (this.peek() == END_OBJECT) {
            this.position++;
            return new ObjectElement(map);
        }

        while (true) {
            this.skipWhitespace();

            if (this.peek() != '"') {
                throw new ParsingException(this.input, "Expected string for object key!", this.position);
            }

            var key = this.parseString().value();
            this.skipWhitespace();
            this.expect(COLON);
            this.skipWhitespace();

            var value = this.readElement();
            map.put(key, value);
            this.skipWhitespace();

            if (this.peek() == END_OBJECT) {
                this.position++;
                break;
            }

            this.expect(COMMA);
        }

        return new ObjectElement(map);
    }

    private boolean isExponent(char character) {
        return character == 'e' || character == 'E';
    }

    private char peek() throws ParsingException {
        if (this.position >= this.input.length()) {
            throw new ParsingException(this.input, "Unexpected end of input!", this.position);
        }

        return this.input.charAt(this.position);
    }

    private void expect(char character) throws ParsingException {
        if (this.peek() != character) {
            throw new ParsingException(this.input, "Expected '" + character + "', got '" + this.peek() + "'!", this.position);
        }

        this.position++;
    }

    private void skipWhitespace() {
        while (this.position < this.input.length() && isWhitespace(this.input.charAt(this.position))) {
            this.position++;
        }
    }
}
