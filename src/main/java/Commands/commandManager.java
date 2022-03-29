package Commands;

import Exceptions.commandNotExistException;

import java.util.HashMap;

public class commandManager {
    HashMap<String, Command> commands = new HashMap<>();

    public commandManager(Command ... commands){
        helpCommand helpcommand = new helpCommand(this.commands);
        this.commands.put(helpcommand.getName(),helpcommand);

        for( Command cmd : commands){
            this.commands.put(cmd.getName(), cmd);
        }
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public void registerCommand(String commandName) throws commandNotExistException{
        if (commands.containsKey(commandName)){
            commands.get(commandName).execute();
        }else{
            throw new commandNotExistException();
        }
    }

}

