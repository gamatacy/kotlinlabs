package console;


import commands.Command;
import commands.CommandManager;
import commands.RegisterCommand;
import exceptions.CommandNotExistException;

import java.io.BufferedReader;
import java.util.HashMap;

/**
 * Read input from user and register commands
 */
public class ConsoleManager implements RegisterCommand {
    private final String username = "user";
    private final CommandManager commandManager;
    private final BufferedReader reader;

    public ConsoleManager(CommandManager commandmanager, BufferedReader reader) {
        this.commandManager = commandmanager;
        this.reader = reader;
    }

    private static boolean running = true;

    private static boolean scriptInput = false;

    public void run() {
        while (running) {
            System.out.print("<" + this.username + ">$");

            try {
                String command = reader.readLine();
                registerCommand(command, commandManager.getCommands());
                scriptInput = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void registerCommand(String commandName, HashMap<String, Command> commands) throws CommandNotExistException {
        String[] cmd = commandName.split(" ");
        if (commands.containsKey(cmd[0]) && cmd.length == 1) {
            commandManager.updateHistory(cmd[0]);
            commands.get(cmd[0]).execute(this.reader);
        } else if (commands.containsKey(cmd[0])) {
            commandManager.updateHistory(cmd[0]);
            commands.get(cmd[0]).setArgument(cmd[1]);
            commands.get(cmd[0]).execute(this.reader);
        } else {
            throw new CommandNotExistException();
        }
    }

    public static void stop() {
        running = false;
    }

    public static void setScriptInput() {
        scriptInput = true;
    }

    public static boolean getScriptInput() {
        return scriptInput;
    }

}


