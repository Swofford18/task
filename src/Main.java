import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Parser parser = new Parser();
        Splitter splitter = new Splitter();
        Validator validator = new Validator();
        Calculator calculator = new Calculator();

        Map<String, String> variables = new HashMap<>();
        String expression = null;
        String input = "";
        while (true) {
            input = scan.nextLine().replaceAll("\\s", "");

            if (Command.of(input.toLowerCase()) != null) {
                if (expression == null) {
                    System.out.println("Enter expression");
                    continue;
                }
                Command currentCommand = Command.of(input.toLowerCase());
                List<String> parts = splitter.split(expression);
                AstNode root;
                try {
                    root = parser.parse(parts);
                } catch(RuntimeException e) {
                    System.out.println("Expression cannot be parsed. Re-enter the expression and variables");
                    expression = null;
                    variables = new HashMap<>();
                    continue;
                }

                switch (currentCommand) {
                    case PRINT : {
                        System.out.println(root);
                        break;
                    }
                    case CALC : {
                        if (checkRequiredVars(parts, variables)) {
                            try {
                                int result = calculator.calculate(root, variables);
                                System.out.println(result);
                            } catch (RuntimeException e) {
                                System.out.println("Expression cannot be calculated. Re-enter the expression and variables");
                                expression = null;
                                variables = new HashMap<>();
                            }
                        } else {
                            System.out.println("Enter more variables before calculation");
                        }
                        break;
                    }
                }
            } else if (expression == null) {
                if (validator.validateExpression(input)) {
                    expression = input;
                    System.out.println("OK");
                } else {
                    System.out.println("Incorrect expression input");
                }
            } else {
                if (validator.validateVariable(input)) {
                    int index = input.indexOf('=');
                    variables.put(input.substring(0, index), input.substring(index + 1));
                    System.out.println("OK");
                } else {
                    System.out.println("Incorrect variable input");
                }
            }
        }
    }

    private static boolean checkRequiredVars(List<String> parts, Map<String, String> variables) {
        Set<String> expressionVars = parts.stream()
                .filter(p -> p.matches("^[A-Za-z].*"))
                .collect(Collectors.toSet());
        return variables.keySet().equals(expressionVars);
    }
}