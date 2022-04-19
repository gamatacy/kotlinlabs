package Commands;

import Exceptions.commandNotExistException;

import java.util.HashMap;

/**
 * Manage commands
 */
public class commandManager {
    private HashMap<String, Command> commands = new HashMap<>();

    /**
     *
     * @param commands - list of commands
     */
    public commandManager(Command ... commands){
        helpCommand helpcommand = new helpCommand(this.commands);
        this.commands.put(helpcommand.getName(),helpcommand);

        for( Command cmd : commands){
            this.commands.put(cmd.getName(), cmd);
        }
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

}

