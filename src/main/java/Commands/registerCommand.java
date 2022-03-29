package Commands;

import Exceptions.commandNotExistException;

import java.util.HashMap;

public interface registerCommand {
    public void registerCommand(String commandName, HashMap<String, Command> commands) throws commandNotExistException;
}
