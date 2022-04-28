package commands;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Manage commands
 */
public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();
    private final ArrayDeque<String> commandHistory = new ArrayDeque<>();

    /**
     * @param commands - list of commands
     */
    public CommandManager(Command... commands) {
        HelpCommand helpCommand = new HelpCommand(this.commands);
        HistoryCommand historyCommand = new HistoryCommand(this.commandHistory);
        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand(this.commands);
        this.commands.put(helpCommand.getName(), helpCommand);
        this.commands.put(historyCommand.getName(), historyCommand);
        this.commands.put(executeScriptCommand.getName(), executeScriptCommand);
        for (Command cmd : commands) {
            this.commands.put(cmd.getName(), cmd);
        }
    }

    public void updateHistory(String command) {
        if (this.commandHistory.size() >= 8) {
            this.commandHistory.removeLast();
        }
        this.commandHistory.addFirst(command);
    }

    public ArrayDeque<String> getCommandHistory() {
        return commandHistory;
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

}

