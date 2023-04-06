import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Parser {

    private static final String OPENING_BRACKET = "(";
    private static final String CLOSING_BRACKET = ")";

    public AstNode parse(List<String> parts) {

        Stack<String> operatorStack = new Stack<>();
        Stack<AstNode> nodes = new Stack<>();

        for(String part : parts) {
            if (OPENING_BRACKET.equals(part)) {
                operatorStack.push(OPENING_BRACKET);
            } else if (CLOSING_BRACKET.equals(part)) {
                boolean foundOpen = false;
                while (!operatorStack.isEmpty()) {
                    String currentOp = operatorStack.pop();
                    if (OPENING_BRACKET.equals(currentOp)) {
                        foundOpen = true;
                        break;
                    } else {
                        addNodeToStack(nodes, currentOp);
                    }
                }
                if (!foundOpen)
                    throw new RuntimeException();
            } else if (Operation.of(part) != null) {
                Operation current = Operation.of(part);
                while (!operatorStack.isEmpty()) {
                    Operation last = Operation.of(operatorStack.peek());

                    if (last == null)
                        break;

                    if (current.comparePriority(last) == 1) {
                        operatorStack.pop();
                        addNodeToStack(nodes, last.getSign());
                    } else {
                        break;
                    }
                }
                operatorStack.push(part);
            } else {
                nodes.push(new AstNode(part, null, null));
            }
        }
        while (!operatorStack.isEmpty()) {
            String currentOp = operatorStack.pop();
            if (currentOp.equals(OPENING_BRACKET))
                throw new RuntimeException();
            addNodeToStack(nodes, currentOp);
        }

        return nodes.peek();
    }

    private void addNodeToStack(Stack<AstNode> nodes, String operator) {

        AstNode right = null;
        AstNode left = null;

        if (!nodes.isEmpty())
            right = nodes.pop();
        if (!nodes.isEmpty())
            left = nodes.pop();

        nodes.push(new AstNode(operator, left, right));
    }
}
