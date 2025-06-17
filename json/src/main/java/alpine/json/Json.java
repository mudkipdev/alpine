package alpine.json;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static alpine.json.JsonUtility.*;

/**
 * A helper class for reading and writing JSON data.
 * @author mudkip
 */
public final class Json {
    private static final JsonReader READER = new JsonReader();
    private static final JsonWriter WRITER = new JsonWriter();

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

    public static void write(File file, Element element, Formatting formatting) {
        write(file.toPath(), element, formatting);
    }

    /**
     * Defines rules for customizing the formatting when writing JSON data.
     * @param indentation The string to use when indenting. Typically empty, a tab character ({@code \t}), or multiple spaces.
     * @param newLine The string to use to terminate a line. Typically empty, a Unix line ending ({@code \n}) or a Windows line ending ({@code \r\n}).
     * @param comma The string to use between values of an object or array. Typically {@code ,} followed by a space.
     * @param colon The string to use between key-value pairs of an object. Typically {@code :} followed by a space.
     */
    public record Formatting(String indentation, String newLine, String comma, String colon) {
        /**
         * Represents a minified format ideal for exchanging data over the network.
         */
        public static final Formatting COMPACT = new Formatting(
                "",
                System.lineSeparator(),
                String.valueOf(COMMA),
                String.valueOf(COLON));

        /**
         * Represents a beautified format ideal for human readability.
         */
        public static final Formatting PRETTY = new Formatting(
                String.valueOf(SPACE).repeat(4),
                System.lineSeparator(),
                COMPACT.comma + SPACE,
                COMPACT.colon + SPACE);
    }
}
