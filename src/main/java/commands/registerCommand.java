package commands;

import exceptions.commandNotExistException;

import java.util.HashMap;

/**
 * Register command from user or script
 */
public interface registerCommand {
    public void registerCommand(String commandName, HashMap<String, Command> commands) throws commandNotExistException;
}
