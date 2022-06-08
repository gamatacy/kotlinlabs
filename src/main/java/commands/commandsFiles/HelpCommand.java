package commands.commandsFiles;

import commands.Command;
import commands.ExecutionResult;

import java.io.BufferedReader;
import java.util.Collection;

/**
 * Print all commands and their descriptions
 */
public class HelpCommand extends Command {
    private final Collection<Command> commands;

    public HelpCommand(Collection<Command> commands) {
        super("help", "вывести справку по доступным командам");
        this.commands = commands;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        String info = "\n";
        for (Command command : commands) {
            info += command.getName() + " : " + command.getDescription() + "\n";
        }
        return ExecutionResult.executionResult(true, info);
    }

}
