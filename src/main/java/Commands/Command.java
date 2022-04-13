package Commands;

import java.util.HashMap;
import java.io.BufferedReader;

/**
 * Abstract class for all commands
 */
public abstract class Command {
    private String name;
    private String description;


    /**
     *
     * @param name
     * @param description
     */
    public Command(String name, String description){
        this.name = name;
        this.description = description;
    }

    /**
     *
     * @return String description
     */
    public String getDescription(){
        return this.description;
    }

    /**
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Execute method for every command
     * @param reader
     */
    public abstract void execute(BufferedReader reader);

    /**
     * Set params for command
     * @param arg
     * @param commands
     */
    public abstract void setArgument(String arg, HashMap<String, Command> commands);

}

/**
 Ready commands:
    help
    info
    show
    add
    execute_script
    history
    exit
    update
    remove by id
    save
    remove all by manufacturer
    head
    remove head
    filter by part number
    print ascending
 */