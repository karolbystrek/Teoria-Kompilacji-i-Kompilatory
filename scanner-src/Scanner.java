import token.Token;
import token.TokenType;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Scanner implements Closeable {

    private final BufferedReader reader;
    private String currentLine;
    private int lineIndex;
    private int columnIndex;

    public Scanner(InputStream inputStream) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.lineIndex = 0;
        this.columnIndex = 0;
        this.currentLine = null;
    }

    public Token nextToken() throws IOException {
        if (currentLine != null) {
            skipWhitespaces();
        }
        while (currentLine == null || columnIndex >= currentLine.length()) {
            try {
                loadNextLine();
            } catch (EOFException e) {
                return null;
            }
            skipWhitespaces();
        }

        StringBuilder tokenValueBuilder = new StringBuilder();
        TokenType tokenValueType = TokenType.UNKNOWN;

        while (columnIndex < currentLine.length()) {
            char c = currentLine.charAt(columnIndex);
            tokenValueBuilder.append(c);

            TokenType newTokenValueType = TokenType.fromValue(tokenValueBuilder.toString());
            if (newTokenValueType == TokenType.UNKNOWN) {
                tokenValueBuilder.deleteCharAt(tokenValueBuilder.length() - 1);
                break;
            } else {
                tokenValueType = newTokenValueType;
                columnIndex++;
            }
        }
        return new Token(tokenValueType, tokenValueBuilder.toString(), lineIndex, columnIndex);
    }

    private void loadNextLine() throws IOException, EOFException {
        currentLine = reader.readLine();
        if (currentLine == null) {
            throw new EOFException();
        }
        lineIndex++;
        columnIndex = 0;
    }

    private void skipWhitespaces() {
        while (columnIndex < currentLine.length()
                && Character.isWhitespace(currentLine.charAt(columnIndex))) {
            columnIndex++;
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
