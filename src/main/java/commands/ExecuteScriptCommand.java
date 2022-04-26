package commands;

import console.ConsoleManager;
import collection.CollectionManager;
import exceptions.CommandNotExistException;
import fileUtils.*;


import java.io.*;
import java.util.HashMap;

/**
 * Execute script from file
 */
public class ExecuteScriptCommand extends Command implements ReadFile, RegisterCommand {
    private CollectionManager collectionManager;
    private FileManager fileManager;
    private BufferedReader script;
    private String path;
    private HashMap<String, Command> commands;


    public ExecuteScriptCommand(CollectionManager collectionmanager, FileManager filemanager){
        super("execute_script","считать и исполнить скрипт из указанного файла");
        this.collectionManager = collectionmanager;
        this.fileManager = filemanager;
    }


    @Override
    public void execute(BufferedReader reader) {
        try{
            readFile(this.path);
            registerCommand("None", commands);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void setArgument(String arg,HashMap<String, Command> commands) {
        this.path = arg;
        this.commands = commands;
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
        if (path != null){
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            this.script = reader;
        }
        else{
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
    public void registerCommand(String commandName, HashMap<String, Command> commands) throws CommandNotExistException {
        while(true){
            try {
                ConsoleManager.setScriptInput();
                String cmd = script.readLine();
                if (cmd != null){
                    String[] command = cmd.split(" ");
                    if (commands.containsKey(command[0]) && command.length == 1){
                        commands.get(command[0]).execute(this.script);

                    }
                    else if(commands.containsKey(command[0])){
                        commands.get(command[0]).setArgument(command[1], commands);
                        commands.get(command[0]).execute(this.script);
                    }
                    else{
                        throw new CommandNotExistException();
                    }
                }
                else{
                    break;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
