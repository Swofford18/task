import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Splitter {

    private final Set<Character> OPERATORS = new HashSet<>(Arrays.asList('+', '-', '/', '*', '(', ')'));
    public List<String> split(String expression) {

        List<String> parts = new ArrayList<>();
        String part = "";

        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);

            if (symbol >= '0' && symbol <= '9') {
                part += symbol;
            } else if (symbol >= 'A' && symbol <= 'z') {
                part += symbol;
            } else if (OPERATORS.contains(symbol)) {
                if (!part.isEmpty()) {
                    parts.add(part);
                    part = "";
                }
                parts.add(String.valueOf(symbol));
            } else {
                throw new RuntimeException("123");
            }
        }

        if (!part.isEmpty()) {
            parts.add(part);
        }
        return parts;
    }
}
