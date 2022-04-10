package Commands;

import java.util.HashMap;
import java.io.BufferedReader;

public abstract class Command {
    private String name;
    private String description;


    public Command(String name, String description){
        this.name = name;
        this.description = description;
    }


    public String getDescription(){
        return this.description;
    }
    public String getName() {
        return name;
    }

    public abstract void execute(BufferedReader reader);

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