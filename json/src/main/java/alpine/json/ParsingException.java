package alpine.json;

public final class ParsingException extends Exception {
    private final int position;

    public ParsingException(String message, int position) {
        super(message);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
