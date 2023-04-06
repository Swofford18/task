import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private final static String EXPRESSION_BASE = "[A-Za-z0-9()+/*-]*";
    private final static String VARIABLE_BASE = "^[A-Za-z][A-Za-z0-9]*=[0-9]*";

    public boolean validateExpression(String input) {
        return validate(input, EXPRESSION_BASE) && validateBrackets(input);
    }

    public boolean validateVariable(String input) {
        return validate(input, VARIABLE_BASE);
    }

    private boolean validateBrackets(String input) {
        Pattern pattern = Pattern.compile("\\([\\w+/*-]*\\)");
        Matcher matcher = pattern.matcher(input);
        do {
            input = matcher.replaceAll("");
            matcher = pattern.matcher(input);
        } while (matcher.find());
        return input.matches("[\\w+/*-]*");
    }

    private boolean validate(String input, String regExp) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
