package commands.commandsFiles;

import commands.Command;
import commands.ExecutionResult;

import java.io.BufferedReader;

/**
 * Stop the programm
 */
public class ExitCommand extends Command {


    public ExitCommand(){
        super("exit","завершить программу");
    }


    @Override
    public ExecutionResult execute(BufferedReader reader) {
        return ExecutionResult.executionResult(true,"Exit success");
    }

}
