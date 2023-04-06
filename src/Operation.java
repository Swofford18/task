import java.util.Arrays;

public enum Operation {
    MULTIPLY("*", 1),
    DIVIDE("/", 1),
    MINUS("-", 2),
    PLUS("+", 2);

    private final String sign;
    private final int priority;
    Operation(String sign, int priority) {
        this.sign = sign;
        this.priority = priority;
    }

    public static Operation of(String sign) {
        return Arrays.stream(Operation.values())
                .filter(o -> o.sign.equals(sign))
                .findAny()
                .orElse(null);
    }

    public int getPriority() {
        return this.priority;
    }

    public String getSign() {
        return this.sign;
    }

    public int comparePriority(Operation o) {
        return Integer.compare(this.priority, o.getPriority());
    }
}
