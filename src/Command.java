import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Command {
    CALC("calc"),
    PRINT("print");
    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command of(String name) {
        return Arrays.stream(values())
                .filter(c -> c.name.equals(name))
                .findAny()
                .orElse(null);
    }
}
