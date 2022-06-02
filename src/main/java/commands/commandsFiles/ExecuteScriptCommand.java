package commands.commandsFiles;


import commands.*;
import utils.*;

import java.io.*;

/**
 * Execute script from file
 */
public class ExecuteScriptCommand extends Command implements CommandWithArgument {
    private final int argumentsCount = 1;
    private String path;
    private CommandManager commandManager;
    private FileManager fileManager;

    public ExecuteScriptCommand(CommandManager commandManager, FileManager fileManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.commandManager = commandManager;
        this.fileManager = fileManager;
    }


    @Override
    public ExecutionResult execute(BufferedReader reader) {
        try {
            File file = new File(this.path);
            BufferedReader scriptReader = new BufferedReader(new FileReader(file));
            return CommandInvoker.invokeScriptCommand(scriptReader, commandManager);
        } catch (FileNotFoundException ef) {
            return ExecutionResult.executionResult(false, "File doesn't exist");
        }
    }

    @Override
    public void setArgument(String[] args) {
        this.path = args[0];
    }

    @Override
    public int getArgumentsCount() {
        return argumentsCount;
    }


}
