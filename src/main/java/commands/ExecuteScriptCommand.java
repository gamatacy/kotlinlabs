package commands;

import console.ConsoleManager;
import exceptions.CommandNotExistException;
import fileUtils.*;


import java.io.*;
import java.util.HashMap;

/**
 * Execute script from file
 */
public class ExecuteScriptCommand extends Command implements ReadFile, RegisterCommand {
    private BufferedReader scriptCommands;
    private String path;
    private HashMap<String, Command> commands;


    public ExecuteScriptCommand(HashMap<String, Command> commands) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.commands = commands;
    }


    @Override
    public void execute(BufferedReader reader) {
        try {
            readFile(this.path);
            registerCommand("None", commands);
            for (int i = 0; i < 20; i++){
                System.out.println("<>$");
            }
        } catch (FileNotFoundException ef) {
            System.out.println("File doesn't exist");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void setArgument(String arg) {
        this.path = arg;
    }

    /**
     * Read file from path
     *
     * @param path
     * @throws IOException
     * @throws CommandNotExistException
     */
    @Override
    public void readFile(String path) throws IOException, CommandNotExistException {
        if (path != null) {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            this.scriptCommands = reader;
        } else {
            throw new CommandNotExistException("Specify an argument in execute_script command!");
        }
    }

    /**
     * Register the commands from file
     *
     * @param commandName
     * @param commands
     * @throws CommandNotExistException
     */
    public void registerCommand(String commandName, HashMap<String, Command> commands){
        while (true) {
            try {
                ConsoleManager.setScriptInput();
                String cmd = scriptCommands.readLine();
                if (cmd != null) {
                    String[] command = cmd.split(" ");
                    if (commands.containsKey(command[0]) && command.length == 1) {
                        commands.get(command[0]).execute(this.scriptCommands);

                    } else if (commands.containsKey(command[0])) {
                        commands.get(command[0]).setArgument(command[1]);
                        commands.get(command[0]).execute(this.scriptCommands);
                    } else {
                        throw new CommandNotExistException();
                    }
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
