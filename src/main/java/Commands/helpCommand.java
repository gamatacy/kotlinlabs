package Commands;

import java.io.BufferedReader;
import java.util.HashMap;

public class helpCommand extends Command{
    private HashMap<String,Command> commands;

    public helpCommand(HashMap<String,Command> commands){
        super("help");
        this.commands = commands;
    }

    @Override
    public void execute(BufferedReader reader) {
        System.out.println(commands.keySet());
    }

    @Override
    public void setArgument(String arg,HashMap<String, Command> commands) {

    }

}
