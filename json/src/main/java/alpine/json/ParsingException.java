package alpine.json;

/**
 * An exception thrown when the JSON parser encounters malformed input.
 * @author mudkip
 */
public final class ParsingException extends Exception {
    private final String input;
    private final int position;

    public ParsingException(String input, String message, int position) {
        super(formatMessage(input, message, position));
        this.input = input;
        this.position = position;
    }

    private static String formatMessage(String input, String message, int position) {
        var line = 1;
        var column = 1;

        if (input.isEmpty()) {
            return String.format("%s (at 1:1)", message);
        }

        for (var index = 0; index < position; index++) {
            if (input.charAt(index) == '\n') {
                line++;
                column = 1;
            } else {
                column++;
            }
        }

        return String.format("%s (at %d:%d)", message, line, column);
    }

    public String getInput() {
        return this.input;
    }

    public int getPosition() {
        return this.position;
    }
}
