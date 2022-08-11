package commands.commandsFiles;


import commands.*;
import utils.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Execute script from file
 */
public class ExecuteScriptCommand extends Command implements CommandWithArgument {
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
    public void setArgument(Object argument) {
        this.path = (String) argument;
    }

}
