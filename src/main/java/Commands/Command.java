package Commands;

import java.util.HashMap;
import java.io.BufferedReader;

public abstract class Command {
    private String name;

    public Command(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void execute(BufferedReader reader);

    public abstract void setArgument(String arg, HashMap<String, Command> commands);

}

/**
 Ready commands:
    help // visual correction + description
    info // add some stuff
    show // visual correction
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