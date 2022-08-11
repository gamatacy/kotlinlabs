package commands;

import java.io.BufferedReader;
import java.io.Serializable;

/**
 * Abstract class for all commands
 */
public abstract class Command implements Serializable {
    private final String name;
    private final String description;


    /**
     * @param name
     * @param description
     */
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Execute method for every command
     *
     * @param reader
     * @return
     */
    public abstract ExecutionResult execute(BufferedReader reader);

}

