import java.util.Map;

public class Calculator {

    public int calculate(AstNode root, Map<String, String> variables) {

        AstNode left = root.getLeft();
        AstNode right = root.getRight();

        if (right == null || left == null) {
            return variables.get(root.getValue()) == null ? Integer.parseInt(root.getValue()) : Integer.parseInt(variables.get(root.getValue()));
        }
        if (left.getLeft() == null && left.getRight() == null) {
            int value = variables.get(left.getValue()) == null ? Integer.parseInt(left.getValue()) : Integer.parseInt(variables.get(left.getValue()));
            switch (Operation.of(root.getValue())) {
                case MULTIPLY : return value * calculate(right, variables);
                case PLUS : return value + calculate(right, variables);
                case DIVIDE : return value / calculate(right, variables);
                case MINUS : return value - calculate(right, variables);
            }
        } else if (right.getLeft() == null && right.getRight() == null) {
            int value = variables.get(right.getValue()) == null ? Integer.parseInt(right.getValue()) : Integer.parseInt(variables.get(right.getValue()));
            switch (Operation.of(root.getValue())) {
                case MULTIPLY : return value * calculate(left, variables);
                case PLUS : return value + calculate(left, variables);
                case DIVIDE : return value / calculate(left, variables);
                case MINUS : return value - calculate(left, variables);
            }
        } else {
            switch (Operation.of(root.getValue())) {
                case MULTIPLY : return calculate(right, variables) * calculate(left, variables);
                case PLUS : return calculate(right, variables) + calculate(left, variables);
                case DIVIDE : return calculate(right, variables) / calculate(left, variables);
                case MINUS : return calculate(right, variables) - calculate(left, variables);
            }
        }
        return 0;
    }
}
