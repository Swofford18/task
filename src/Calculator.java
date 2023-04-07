import java.util.Map;

public class Calculator {

    public int calculate(AstNode root, Map<String, String> variables) throws IllegalArgumentException {
        if (root == null) {
            throw new RuntimeException("The root node is null.");
        }

        String value = root.getValue();
        if (value == null) {
            throw new RuntimeException("The value is null");
        }

        if (isNumber(value)) {
            return Integer.parseInt(root.getValue());
        }

        Operation operation = Operation.of(root.getValue());

        AstNode left = root.getLeft();
        AstNode right = root.getRight();

        int leftValue = calculateOperand(left, variables);
        int rightValue = calculateOperand(right, variables);

        if (operation == Operation.DIVIDE && rightValue == 0) {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }

        switch (operation) {
            case PLUS:
                return leftValue + rightValue;
            case MINUS:
                return leftValue - rightValue;
            case MULTIPLY:
                return leftValue * rightValue;
            case DIVIDE:
                return leftValue / rightValue;
            default:
                throw new IllegalArgumentException("Unsupported operation: " + operation);
        }
    }

    private int calculateOperand(AstNode node, Map<String, String> variables) {
        if (node == null) {
            return 0;
        }

        if (node.isLeaf()) {
            String value = variables.get(node.getValue());
            return value == null ? Integer.parseInt(node.getValue()) : Integer.parseInt(value);
        }

        return calculate(node, variables);
    }

    private boolean isNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
