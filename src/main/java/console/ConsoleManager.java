package console;


import commands.CommandHistory;
import commands.CommandManager;
import commands.CommandInvoker;
import commands.ExecutionResult;

import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * Read input from user and return commands results
 */
public class ConsoleManager {
    private String username = "user";
    private final CommandManager commandManager;
    private final BufferedReader reader;
    private final CommandHistory history;
    private final PrintStream printStream;

    public ConsoleManager(CommandManager commandmanager, BufferedReader reader, PrintStream printStream) {
        this.commandManager = commandmanager;
        this.history = new CommandHistory();
        this.reader = reader;
        this.printStream = printStream;
    }

    public void run() {
        while (true) {
            printStream.print("<" + this.username + ">$");
            try {
                String command = reader.readLine();

                if (command.length() == 0) {
                    continue;
                }

                ExecutionResult executionResult = CommandInvoker.invokeUserCommand(command.split(" "), reader, commandManager);

                if (executionResult.getResult()) {
                    if (executionResult.getMessage().equals("Exit success")) {
                        break;
                    }
                    if (executionResult.getMessage().equals("history")) {
                        history.printHistory(printStream);
                    } else {
                       printStream.println(executionResult.getMessage());
                    }
                    history.updateHistory(command.split(" ")[0]);
                } else {
                    printStream.println(executionResult.getMessage());
                }
            } catch (Exception e) {
                printStream.println(e.getMessage());
            }

        }
        try {
            reader.close();
        } catch (Exception e) {
            printStream.println(e.getMessage());
        }
    }

}




