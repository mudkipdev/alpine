package alpine.json;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A helper class for reading and writing JSON data.
 * @author mudkip
 */
public final class Json {
    private static final JsonReader READER = new JsonReader();
    private static final JsonWriter WRITER = new JsonWriter();

    // Structural
    static char BEGIN_OBJECT = '{';
    static char END_OBJECT = '}';
    static char BEGIN_ARRAY = '[';
    static char END_ARRAY = ']';
    static char COMMA = ',';
    static char COLON = ':';

    // Strings
    static char QUOTE = '"';
    static char BACKSLASH = '\\';
    static char UNICODE_ESCAPE = 'u';

    // Numbers
    static char PLUS = '+';
    static char MINUS = '-';
    static char BEGIN_DECIMAL = '.';

    // Whitespace
    static char SPACE = ' ';
    static char TAB = '\t';
    static char LINE_FEED = '\n';
    static char CARRIAGE_RETURN = '\r';

    // Literals
    static String NULL = "null";
    static String TRUE = "true";
    static String FALSE = "false";

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

    public static Element read(File file) throws ParsingException {
        return read(file.toPath());
    }

    public static String write(Element element, JsonFormatting formatting) {
        return WRITER.write(element, formatting);
    }

    public static void write(Path path, Element element, JsonFormatting formatting) {
        try {
            Files.writeString(path, write(element, formatting));
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON to file!", e);
        }
    }

    public static void write(File file, Element element, JsonFormatting formatting) {
        write(file.toPath(), element, formatting);
    }

    static boolean isControlCharacter(char character) {
        return character <= 0x1F;
    }

    static boolean isWhitespaceCharacter(char character) {
        return character == SPACE
                || character == TAB
                || character == LINE_FEED
                || character == CARRIAGE_RETURN;
    }
}
