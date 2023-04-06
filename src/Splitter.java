import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Splitter {

    private final Set<Character> OPERATORS = new HashSet<>(Arrays.asList('+', '-', '/', '*', '(', ')'));

    /**
     * Breaks the expression into pieces "(1+1)" -> ["(","1","+","1",")"]
     *
     * @param expression expression string without spaces
     */
    public List<String> split(String expression) {

        List<String> parts = new ArrayList<>();
        StringBuilder part = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);

            if (symbol >= '0' && symbol <= '9') {
                part.append(symbol);
            } else if (symbol >= 'A' && symbol <= 'z') {
                part.append(symbol);
            } else if (OPERATORS.contains(symbol)) {
                if (part.length() > 0) {
                    parts.add(part.toString());
                    part = new StringBuilder();
                }
                parts.add(String.valueOf(symbol));
            } else {
                throw new RuntimeException("123");
            }
        }

        if (part.length() > 0) {
            parts.add(part.toString());
        }
        return parts;
    }
}
