package Commands;

import java.util.HashMap;

public abstract class Command {
    private String name;

    public Command(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void execute();

    public abstract void setArgument(String arg, HashMap<String, Command> commands);

}

/**
 Ready commands:
    help // visual correction + description
    info // add some stuff
    show // visual correctection
    add
    execute_script
    history
    exit
 */