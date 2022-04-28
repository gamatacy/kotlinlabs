package commands;

import exceptions.CommandNotExistException;

import java.util.HashMap;

/**
 * Register command from user or script
 */
public interface RegisterCommand {
    void registerCommand(String commandName, HashMap<String, Command> commands) throws CommandNotExistException;
}
