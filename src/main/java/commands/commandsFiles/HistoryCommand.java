package commands.commandsFiles;

import commands.Command;
import commands.ExecutionResult;

import java.io.BufferedReader;
import java.util.ArrayDeque;

/**
 * Print last 8 used commands
 */
public class HistoryCommand extends Command {

    public HistoryCommand() {
        super("history", "вывести последние 8 команд");
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        return ExecutionResult.executionResult(true,"history");
    }

}
