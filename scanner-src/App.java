import token.Token;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        String input = "2+3*(76+8/3)+ 3*(9-3)";

        List<Token> tokens = new LinkedList<>();

        try (Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()))) {
            Token token = null;
            while ((token = scanner.nextToken()) != null) {
                tokens.add(token);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Token token : tokens) {
            System.out.println(token.toString());
        }
    }
}
