package commands;

public class ExecutionResult {
    private Boolean result;
    private String message;

    private ExecutionResult(boolean success, String message) {
        this.result = success;
        this.message = message;
    }

    public static ExecutionResult executionResult(boolean result, String message) {
        return new ExecutionResult(result, message);
    }

    public Boolean getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
