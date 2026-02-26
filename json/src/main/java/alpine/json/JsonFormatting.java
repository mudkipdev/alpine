package alpine.json;

/**
 * Defines rules for customizing the formatting when writing JSON data.
 *
 * @param indentation The string to use when indenting. Typically empty, a tab character ({@code \t}), or multiple spaces.
 * @param newLine The string to use to terminate a line. Typically empty, a Unix line ending ({@code \n}) or a Windows line ending ({@code \r\n}).
 * @param comma The string to use between values of an object or array. Typically {@code ,} followed by a space.
 * @param colon The string to use between key-value pairs of an object. Typically {@code :} followed by a space.
 */
public record JsonFormatting(String indentation, String newLine, String comma, String colon) {
    /**
     * Represents a minified format ideal for exchanging data over the network.
     */
    public static final JsonFormatting COMPACT = new JsonFormatting(
            "",
            "\n",
            String.valueOf(Json.COMMA),
            String.valueOf(Json.COLON));

    /**
     * Represents a single-line beautified format with spaces between values.
     */
    public static final JsonFormatting INLINE = new JsonFormatting(
            "",
            "\n",
            COMPACT.comma + Json.SPACE,
            COMPACT.colon + Json.SPACE);

    /**
     * Represents a multi-line beautified format ideal for human readability.
     */
    public static final JsonFormatting PRETTY = new JsonFormatting(
            String.valueOf(Json.SPACE).repeat(4),
            "\n",
            String.valueOf(Json.COMMA),
            COMPACT.colon + Json.SPACE);
}
