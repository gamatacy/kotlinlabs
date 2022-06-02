package commands;

import exceptions.InvalidValueException;

import java.util.Collection;
import java.util.HashMap;

/**
 * Manage commands
 */
public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();

    public CommandManager() {
    }

    public void registerCommands(Command... commands) {
        for (Command cmd : commands) {
            this.commands.put(cmd.getName(), cmd);
        }
    }

    public Collection<Command> getCommandsInfo() {
        return commands.values();
    }

    public Command getCommand(String commandName) throws InvalidValueException {
        Command cmd = commands.get(commandName);
        if (cmd != null) {
            return cmd;
        } else {
            throw new InvalidValueException("Command doesn't exist");
        }
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

}

