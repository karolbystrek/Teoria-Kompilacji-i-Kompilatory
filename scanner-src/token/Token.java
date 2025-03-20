package token;

public class Token {

    private final TokenType type;
    private final String value;
    int line;
    int column;

    public Token(TokenType type, String value, int line, int column) {
        this.type = type;
        this.value = value;
        this.line = line;
        this.column = column;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Type: "
                + type.name()
                + ", Value: "
                + value
                + ", Line: "
                + line
                + ", Column: "
                + column;
    }
}
