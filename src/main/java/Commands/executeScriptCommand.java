package Commands;

import Collection.collectionManager;
import Exceptions.commandNotExistException;
import fileUtils.*;
import org.apache.commons.csv.CSVRecord;


import java.io.*;
import java.util.HashMap;


public class executeScriptCommand extends Command implements readFile, registerCommand{
    private collectionManager collectionManager;
    private fileManager fileManager;
    private BufferedReader script;
    private String path;
    private HashMap<String, Command> commands;

    public executeScriptCommand(collectionManager collectionmanager,fileManager filemanager){
        super("execute_script");
        this.collectionManager = collectionmanager;
        this.fileManager = filemanager;
    }

    @Override
    public void execute() {
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

    public void readScript(String path) throws IOException {

    }

    @Override
    public void readFile(String path) throws IOException, commandNotExistException{
        if (path != null){
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
        this.script = reader;}
        else{
            throw new commandNotExistException("Specify an argument in execute_script command!");
        }
    }

    public void registerCommand(String commandName, HashMap<String, Command> commands) throws commandNotExistException {
        while(true){
            try {
                String cmd = script.readLine();
                if (cmd != null){
                    String[] command = cmd.split(" ");
                    if (commands.containsKey(command[0]) && command.length == 1){
                        commands.get(command[0]).execute();
                    }
                    else if(commands.containsKey(command[0])){
                        commands.get(command[0]).setArgument(command[1], commands);
                        commands.get(command[0]).execute();
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
