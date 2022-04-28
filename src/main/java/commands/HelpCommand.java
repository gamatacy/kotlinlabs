package commands;

import java.io.BufferedReader;
import java.util.HashMap;

/**
 * Print all commands and their descriptions
 */
public class HelpCommand extends Command {
    private final HashMap<String, Command> commands;

    public HelpCommand(HashMap<String, Command> commands) {
        super("help", "вывести справку по доступным командам");
        this.commands = commands;
    }

    @Override
    public void execute(BufferedReader reader) {
        for (Command cmd : commands.values()) {
            System.out.println(cmd.getName() + ": " + cmd.getDescription());
        }
    }

    @Override
    public void setArgument(String arg) {
    }

}
