package commands;


import commands.commandsFiles.AddCommand;
import commands.commandsFiles.ExecuteScriptCommand;
import commands.commandsFiles.ExitCommand;
import exceptions.InvalidValueException;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Invoke from user or script
 */
public class CommandInvoker {
    public static ExecutionResult invokeUserCommand(String[] data, BufferedReader reader, CommandManager commandManager) {
        try {
            Command command = commandManager.getCommand(data[0]);
            ArrayList<Object> args = new ArrayList<>();
            if (data.length > 1) {
                for (int i = 1; i < data.length; i++) {
                    args.add(data[i]);
                }
            }
            if (command instanceof CommandWithArgument) {
                ((CommandWithArgument) command).setArgument(args);
            }
            return command.execute(reader);
        } catch (Exception e) {
            return ExecutionResult.executionResult(false, e.getMessage());
        }
    }

    public static ExecutionResult invokeScriptCommand(BufferedReader reader, CommandManager commandManager) {
        String result = "\n";
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] input = line.split(" ");
                if(input[0].isEmpty()){
                    break;
                }
                Command command = commandManager.getCommand(input[0]);
                if (command.getClass() == AddCommand.class) {
                    ((AddCommand) command).setArgument(null);
                    ((AddCommand) command).setScriptMode();
                    command.execute(reader);
                } else if (command.getClass() == ExecuteScriptCommand.class) {
                    throw new InvalidValueException("Recursion detected");
                } else if (command.getClass() == ExitCommand.class) {
                    return command.execute(reader);
                } else {
                    result += command.execute(reader).getMessage() + "\n";
                }
            }
        } catch (InvalidValueException ie) {
            return ExecutionResult.executionResult(false, result + "Script not fully executed: " + ie.getMessage() + "\n");
        } catch (Exception e) {
            return ExecutionResult.executionResult(false, result + "Script not fully executed\n");
        }
        return ExecutionResult.executionResult(true, result + "Script executed\n");
    }
}

