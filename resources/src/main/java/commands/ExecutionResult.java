package commands;

import java.io.Serializable;

public class ExecutionResult implements Serializable {
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
