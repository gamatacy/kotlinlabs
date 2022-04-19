package commands;

import console.consoleManager;
import collection.collectionManager;
import exceptions.commandNotExistException;
import fileUtils.*;


import java.io.*;
import java.util.HashMap;

/**
 * Execute script from file
 */
public class executeScriptCommand extends Command implements readFile, registerCommand{
    private collectionManager collectionManager;
    private fileManager fileManager;
    private BufferedReader script;
    private String path;
    private HashMap<String, Command> commands;


    public executeScriptCommand(collectionManager collectionmanager,fileManager filemanager){
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
     * @throws commandNotExistException
     */
    @Override
    public void readFile(String path) throws IOException, commandNotExistException{
        if (path != null){
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            this.script = reader;
        }
        else{
            throw new commandNotExistException("Specify an argument in execute_script command!");
        }
    }

    /**
     * Register the commands from file
     *
     * @param commandName
     * @param commands
     * @throws commandNotExistException
     */
    public void registerCommand(String commandName, HashMap<String, Command> commands) throws commandNotExistException {
        while(true){
            try {
                consoleManager.setScriptInput();
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
                        throw new commandNotExistException();
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
