package alpine.json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public final class Json {
    private static final JsonReader READER = new JsonReader();
    private static final JsonWriter WRITER = new JsonWriter();

    // Structural
    static final char BEGIN_OBJECT = '{';
    static final char END_OBJECT = '}';
    static final char BEGIN_ARRAY = '[';
    static final char END_ARRAY = ']';
    static final char COMMA = ',';
    static final char COLON = ':';

    // Strings
    static final char QUOTE = '"';
    static final char BACKSLASH = '\\';
    static final char SLASH = '/';

    // Whitespace
    static final char SPACE = ' ';
    static final char TAB = '\t';
    static final char LINE_FEED = '\n';
    static final char CARRIAGE_RETURN = '\r';

    // Literals
    static final String NULL = "null";
    static final String TRUE = "true";
    static final String FALSE = "false";

    // Other
    static final char BACKSPACE = '\b';
    static final char FORM_FEED = '\f';

    // Escaping
    static final Map<Character, Character> CHARACTER_TO_ESCAPE = Map.of(
            QUOTE, QUOTE,
            BACKSLASH, BACKSLASH,
            BACKSPACE, 'b',
            FORM_FEED, 'f',
            LINE_FEED, 'n',
            CARRIAGE_RETURN, 'r',
            TAB, 't');

    static final Map<Character, Character> ESCAPE_TO_CHARACTER = Map.of(
            'b', BACKSPACE,
            'f', FORM_FEED,
            'n', LINE_FEED,
            'r', CARRIAGE_RETURN,
            't', TAB,
            QUOTE, QUOTE,
            BACKSLASH, BACKSLASH,
            SLASH, SLASH);

    private Json() {

    }

    public static Element read(String string) throws ParsingException {
        return READER.read(string);
    }

    public static Element read(Path path) throws ParsingException {
        try {
            return read(Files.readString(path));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON from file!", e);
        }
    }

    public static String write(Element element, Formatting formatting) {
        return WRITER.write(element, formatting);
    }

    public static void write(Path path, Element element, Formatting formatting) {
        try {
            Files.writeString(path, write(element, formatting));
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON to file!", e);
        }
    }

    static boolean isWhitespace(char character) {
        return character == SPACE
                || character == TAB
                || character == LINE_FEED
                || character == CARRIAGE_RETURN;
    }

    public record Formatting(String indentation, String newLine, String comma, String colon) {
        public static final Formatting COMPACT = new Formatting(
                "",
                String.valueOf(LINE_FEED),
                String.valueOf(COMMA),
                String.valueOf(COLON));

        public static final Formatting PRETTY = new Formatting(
                String.valueOf(SPACE).repeat(4),
                String.valueOf(LINE_FEED),
                COMPACT.comma + SPACE,
                COMPACT.colon + SPACE);
    }
}
