package Commands;

import java.util.HashMap;

public class helpCommand extends Command{
    private HashMap<String,Command> commands;

    public helpCommand(HashMap<String,Command> commands){
        super("help");
        this.commands = commands;
    }

    @Override
    public void execute() {
        System.out.println(commands.keySet());
    }

}
