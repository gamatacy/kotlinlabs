package commands;

import java.io.BufferedReader;
import java.util.HashMap;

/**
 * Print all commands and their descriptions
 */
public class helpCommand extends Command{
    private HashMap<String,Command> commands;

    public helpCommand(HashMap<String, Command> commands){
        super("help","вывести справку по доступным командам");
        this.commands = commands;
    }

    @Override
    public void execute(BufferedReader reader) {
        //System.out.println(commands.keySet());
        for(Command cmd : commands.values()){
            System.out.println(cmd.getName() + ": " + cmd.getDescription());
        }
    }

    @Override
    public void setArgument(String arg,HashMap<String, Command> commands) {
        //this.commands = commands;
    }

}
