package alpine.json;

import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static alpine.json.Element.*;
import static alpine.json.JsonUtility.*;
import static java.lang.Character.isDigit;

@ApiStatus.Internal
final class JsonReader {
    private String input;
    private char[] characters;
    private int position;

    Element read(String input) throws ParsingException {
        this.input = input;
        this.characters = input.toCharArray();
        this.position = 0;
        this.skipWhitespace();
        return this.readElement();
    }

    private Element readElement() throws ParsingException {
        var character = this.peek();

        if (character == QUOTE) {
            return this.parseString();
        } else if (isDigit(character) || character == MINUS) {
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
        var length = this.characters.length;

        if (this.position + 4 <= length
                && this.characters[this.position + 0] == 'n'
                && this.characters[this.position + 1] == 'u'
                && this.characters[this.position + 2] == 'l'
                && this.characters[this.position + 3] == 'l') {
            this.position += 4;
            return nil();
        }

        throw new ParsingException(this.input, "Failed to parse null!", this.position);
    }

    private BooleanElement parseBoolean() throws ParsingException {
        var length = this.characters.length;

        if (this.position + 4 <= length
                && this.characters[this.position + 0] == 't'
                && this.characters[this.position + 1] == 'r'
                && this.characters[this.position + 2] == 'u'
                && this.characters[this.position + 3] == 'e') {
            this.position += 4;
            return bool(true);
        }

        if (this.position + 5 <= length
                && this.characters[this.position + 0] == 'f'
                && this.characters[this.position + 1] == 'a'
                && this.characters[this.position + 2] == 'l'
                && this.characters[this.position + 3] == 's'
                && this.characters[this.position + 4] == 'e') {
            this.position += 5;
            return bool(false);
        }

        throw new ParsingException(this.input, "Failed to parse boolean!", this.position);
    }

    private NumberElement parseNumber() throws ParsingException {
        var start = this.position;
        var length = this.characters.length;

        // negative sign
        if (this.peek() == MINUS) {
            this.position++;
        }

        // integer part
        if (this.peek() == '0') {
            this.position++;
        } else if (this.peek() >= '1' && this.peek() <= '9') {
            while (this.position < length && isDigit(this.characters[this.position])) {
                this.position++;
            }
        } else {
            throw new ParsingException(this.input, "Expected a digit after the minus sign!", this.position);
        }

        // fraction part
        if (this.position < length && this.characters[this.position] == BEGIN_DECIMAL) {
            this.position++;
            if (this.position >= length || !isDigit(this.characters[this.position])) {
                throw new ParsingException(this.input, "Expected digit(s) after the decimal point!", this.position);
            }

            while (this.position < length && isDigit(this.characters[this.position])) {
                this.position++;
            }
        }

        // exponent part
        if (this.position < length) {
            var character = this.characters[this.position];

            if (character == 'e' || character == 'E') {
                this.position++;

                if (this.position < length && (this.characters[this.position] == PLUS || this.characters[this.position] == MINUS)) {
                    this.position++;
                }

                if (this.position >= length || !isDigit(this.characters[this.position])) {
                    throw new ParsingException(this.input, "Expected digit(s) in exponent!", this.position);
                }

                while (this.position < length && isDigit(this.characters[this.position])) {
                    this.position++;
                }
            }
        }

        if (this.position < length) {
            var character = this.characters[this.position];

            if (!isWhitespace(character) && character != COMMA && character != END_OBJECT && character != END_ARRAY) {
                throw new ParsingException(this.input, "Invalid character after number!", this.position);
            }
        }

        var text = new String(this.characters, start, this.position - start);

        try {
            var value = Double.parseDouble(text);

            if (Double.isInfinite(value) || Double.isNaN(value)) {
                throw new ParsingException(this.input, "Number out of range: " + text, start);
            }

            return number(value);
        } catch (NumberFormatException exception) {
            throw new ParsingException(this.input, "Invalid number \"" + text + "\"!", start);
        }
    }

    private StringElement parseString() throws ParsingException {
        return string(this.parseRawString());
    }

    private String parseRawString() throws ParsingException {
        this.expect(QUOTE);
        var start = this.position;
        var length = this.characters.length;

        // fast path: scan for closing quote with no escape sequences
        while (this.position < length) {
            var character = this.characters[this.position];

            if (character == QUOTE) {
                return new String(this.characters, start, this.position++ - start);
            }

            if (character == BACKSLASH) {
                break; // fall through
            }

            if (isControl(character)) {
                throw new ParsingException(this.input, "Unescaped control character in string!", this.position);
            }

            this.position++;
        }

        if (this.position >= length) {
            throw new ParsingException(this.input, "Unterminated string!", this.position);
        }

        var builder = new StringBuilder();
        builder.append(this.characters, start, this.position - start);

        while (true) {
            if (this.position >= length) {
                throw new ParsingException(this.input, "Unterminated string!", this.position);
            }

            var character = this.characters[this.position++];

            if (character == QUOTE) {
                break;
            }

            if (character == BACKSLASH) {
                if (this.position >= length) {
                    throw new ParsingException(this.input, "Unterminated escape sequence!", this.position);
                }

                var escapeCharacter = this.characters[this.position++];

                if (escapeCharacter == UNICODE_ESCAPE) {
                    if (this.position + 4 > length) {
                        throw new ParsingException(this.input, "Invalid unicode escape", this.position);
                    }

                    var hex = new String(this.characters, this.position, 4);

                    try {
                        builder.append((char) Integer.parseInt(hex, 16));
                    } catch (NumberFormatException e) {
                        throw new ParsingException(this.input, "Invalid unicode escape: \\u" + hex, this.position);
                    }

                    this.position += 4;
                } else {
                    builder.append(switch (escapeCharacter) {
                        case '"'  -> '"';
                        case '\\' -> '\\';
                        case '/'  -> '/';
                        case 'b'  -> '\b';
                        case 'f'  -> '\f';
                        case 'n'  -> '\n';
                        case 'r'  -> '\r';
                        case 't'  -> '\t';
                        default -> throw new ParsingException(this.input, "Invalid escape character: \\" + escapeCharacter, this.position);
                    });
                }
            } else if (isControl(character)) {
                throw new ParsingException(this.input, "Unescaped control character in string!", this.position);
            } else {
                builder.append(character);
            }
        }

        return builder.toString();
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
            elements.add(this.readElement());
            this.skipWhitespace();

            if (this.peek() == END_ARRAY) {
                this.position++;
                break;
            }

            this.expect(COMMA);
        }

        return new ArrayElement(elements);
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

            if (this.peek() != QUOTE) {
                throw new ParsingException(this.input, "Expected string for object key!", this.position);
            }

            var key = this.parseRawString();
            this.skipWhitespace();
            this.expect(COLON);
            this.skipWhitespace();

            map.put(key, this.readElement());
            this.skipWhitespace();

            if (this.peek() == END_OBJECT) {
                this.position++;
                break;
            }

            this.expect(COMMA);
        }

        return new ObjectElement(map);
    }

    private char peek() throws ParsingException {
        if (this.position >= this.characters.length) {
            throw new ParsingException(this.input, "Unexpected end of input!", this.position);
        }

        return this.characters[this.position];
    }

    private void expect(char character) throws ParsingException {
        var actualCharacter = this.peek();

        if (actualCharacter != character) {
            throw new ParsingException(this.input, "Expected '" + character + "', got '" + actualCharacter + "'!", this.position);
        }

        this.position++;
    }

    private void skipWhitespace() {
        while (this.position < this.characters.length && isWhitespace(this.characters[this.position])) {
            this.position++;
        }
    }
}
