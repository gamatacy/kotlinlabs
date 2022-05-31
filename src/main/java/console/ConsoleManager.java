package console;


import commands.CommandHistory;
import commands.CommandManager;
import commands.CommandInvoker;
import commands.ExecutionResult;

import java.io.BufferedReader;

/**
 * Read input from user and register commands
 */
public class ConsoleManager {
    private String username = "user";
    private final CommandManager commandManager;
    private final BufferedReader reader;
    private final CommandHistory history;

    public ConsoleManager(CommandManager commandmanager, BufferedReader reader) {
        this.commandManager = commandmanager;
        this.history = new CommandHistory();
        this.reader = reader;
    }

    public void run() {
        while (true) {
            System.out.print("<" + this.username + ">$");
            try {
                String command = reader.readLine();

                if (command.length() == 0) {
                    continue;
                }

                ExecutionResult executionResult = CommandInvoker.invokeUserCommand(command.split(" "), reader, commandManager);

                if (executionResult.getResult()) {
                    if (executionResult.getMessage().equalsIgnoreCase("Exit success")) {
                        break;
                    }
                    if (executionResult.getMessage().equalsIgnoreCase("history")) {
                        history.printHistory();
                    } else {
                        System.out.println(executionResult.getMessage());
                    }
                    history.updateHistory(command.split(" ")[0]);
                } else {
                    System.out.println(executionResult.getMessage());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        try {
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}




