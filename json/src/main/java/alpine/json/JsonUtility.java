package alpine.json;

import org.jetbrains.annotations.ApiStatus;

import java.util.Map;

/**
 * Represents constants internal to the JSON parser.
 * <p>
 * Includes things like which characters to escape within a string.
 */
@ApiStatus.Internal
interface JsonUtility {
    static boolean isControl(char character) {
        return character <= 0x1F;
    }

    static boolean isWhitespace(char character) {
        return character == SPACE
                || character == TAB
                || character == LINE_FEED
                || character == CARRIAGE_RETURN;
    }

    // Structural
    char BEGIN_OBJECT = '{';
    char END_OBJECT = '}';
    char BEGIN_ARRAY = '[';
    char END_ARRAY = ']';
    char COMMA = ',';
    char COLON = ':';

    // Strings
    char QUOTE = '"';
    char BACKSLASH = '\\';
    char SLASH = '/';
    char UNICODE_ESCAPE = 'u';

    // Numbers
    char PLUS = '+';
    char MINUS = '-';
    char EXPONENT = 'e';
    char BEGIN_DECIMAL = '.';

    // Whitespace
    char SPACE = ' ';
    char TAB = '\t';
    char LINE_FEED = '\n';
    char CARRIAGE_RETURN = '\r';

    // Literals
    String NULL = "null";
    String TRUE = "true";
    String FALSE = "false";

    // Other
    char BACKSPACE = '\b';
    char FORM_FEED = '\f';

    // Escaping
    Map<Character, Character> CHARACTER_TO_ESCAPE = Map.of(
            QUOTE, QUOTE,
            BACKSLASH, BACKSLASH,
            BACKSPACE, 'b',
            FORM_FEED, 'f',
            LINE_FEED, 'n',
            CARRIAGE_RETURN, 'r',
            TAB, 't');

    Map<Character, Character> ESCAPE_TO_CHARACTER = Map.of(
            'b', BACKSPACE,
            'f', FORM_FEED,
            'n', LINE_FEED,
            'r', CARRIAGE_RETURN,
            't', TAB,
            QUOTE, QUOTE,
            BACKSLASH, BACKSLASH,
            SLASH, SLASH);
}
