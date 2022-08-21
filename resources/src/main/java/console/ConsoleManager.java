package console;


import commands.CommandHistory;
import commands.CommandInvoker;
import commands.CommandManager;
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

    public ConsoleManager(CommandManager commandmanager, BufferedReader reader, PrintStream printStream, String username) {
        this.commandManager = commandmanager;
        this.history = new CommandHistory();
        this.reader = reader;
        this.printStream = printStream;
        this.username = username;
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


                switch (executionResult.getMessage()) {
                    case ("Exit success") -> {
                        System.out.println(executionResult.getMessage());
                    }
                    case ("history") -> {
                        history.printHistory(printStream);
                        continue;
                    }
                    default -> {
                        history.updateHistory(command.split(" ")[0]);
                        printStream.println(executionResult.getMessage());
                        continue;
                    }
                }

            break;

            } catch (Exception e) {
                printStream.println(e.getMessage());
            }
        }
    }

}




