package commands;

import java.io.BufferedReader;

/**
 * Abstract class for all commands
 */
public abstract class Command {
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

