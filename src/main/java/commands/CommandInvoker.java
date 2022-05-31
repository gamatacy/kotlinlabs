package commands;


import commands.commandsFiles.AddCommand;
import exceptions.InvalidValueException;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;


public class CommandInvoker {
    public static ExecutionResult invokeUserCommand(String[] data, BufferedReader reader, CommandManager commandManager) {
        try {
            Command command = commandManager.getCommand(data[0]);
            if (command instanceof CommandWithArgument) {
                if (data.length - 1 >= ((CommandWithArgument) command).getArgumentsCount()) {
                    ArrayList<String> args = new ArrayList<String>(Arrays.asList(data));
                    args.remove(0);
                    ((CommandWithArgument) command).setArgument(args.toArray(new String[0]));
                    return command.execute(reader);
                } else {
                    throw new InvalidValueException("Command expected " + ((CommandWithArgument) command).getArgumentsCount() + " arguments, but " + (data.length - 1) + " were given");
                }
            } else {
                return command.execute(reader);
            }
        } catch (Exception e) {
            return ExecutionResult.executionResult(false, e.getMessage());
        }
    }

    public static ExecutionResult invokeScriptCommand(BufferedReader reader, CommandManager commandManager) {
        try {
            while (reader != null) {
                String[] input = reader.readLine().split(" ");
                Command command = commandManager.getCommand(input[0]);
                if (command.getClass() == AddCommand.class) {
                    ((AddCommand) command).setArgument(new String[2]);
                    command.execute(reader);
                } else if (command instanceof CommandWithArgument) {
                    String[] args = new String[((CommandWithArgument) command).getArgumentsCount()];
                    for (int j = 1; j < input.length; j++) {
                        args[j - 1] = input[j];
                    }
                    ((CommandWithArgument) command).setArgument(args);
                    System.out.println(command.execute(reader).getMessage());
                } else {
                    System.out.println(command.execute(reader).getMessage());
                }
            }
        } catch (Exception e) {return ExecutionResult.executionResult(false, "Script not executed");}
        return ExecutionResult.executionResult(true, "Script executed");
    }
}

