package token;

public enum TokenType {
    UNSIGNED_INTEGER,
    IDENTIFIER,
    PLUS,
    MINUS,
    MULTIPLICATION,
    DIVISION,
    EQUALS,
    LEFT_BRACKET,
    RIGHT_BRACKET,
    UNKNOWN;

    public static TokenType fromValue(String value) {
        if (value.equals("+")) {
            return PLUS;
        } else if (value.equals("-")) {
            return MINUS;
        } else if (value.equals("*")) {
            return MULTIPLICATION;
        } else if (value.equals("/")) {
            return DIVISION;
        } else if (value.equals("=")) {
            return EQUALS;
        } else if (value.equals("(")) {
            return LEFT_BRACKET;
        } else if (value.equals(")")) {
            return RIGHT_BRACKET;
        }

        if (isUnsignedInteger(value)) {
            return UNSIGNED_INTEGER;
        } else if (isIdentifier(value)) {
            return IDENTIFIER;
        }

        return UNKNOWN;
    }

    private static boolean isIdentifier(String value) {
        if (value.isEmpty() || Character.isDigit(value.charAt(0))) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isUnsignedInteger(String value) {
        if (value.isEmpty()) {
            return false;
        }

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
