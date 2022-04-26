package commands;

import java.util.HashMap;

/**
 * Manage commands
 */
public class CommandManager {
    private HashMap<String, Command> commands = new HashMap<>();

    /**
     *
     * @param commands - list of commands
     */
    public CommandManager(Command ... commands){
        HelpCommand helpcommand = new HelpCommand(this.commands);
        this.commands.put(helpcommand.getName(),helpcommand);

        for( Command cmd : commands){
            this.commands.put(cmd.getName(), cmd);
        }
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

}

