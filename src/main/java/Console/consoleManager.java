package Console;


import Commands.Command;
import Commands.commandManager;
import Commands.registerCommand;
import Exceptions.commandNotExistException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;

public class consoleManager implements registerCommand {
    private commandManager CManager;
    private static ArrayDeque<String> commandHistory = new ArrayDeque<>();
    public consoleManager(commandManager commandmanager){
        this.CManager = commandmanager;
    }
    private static boolean stop = true;


    public void run() throws IOException {
        while(stop) {
            System.out.print("<user>$ ");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


            try {

                String command = reader.readLine();

                registerCommand(command, CManager.getCommands());

            } catch (Exception e) {

                System.out.println(e.getMessage());

            }

        }
    }

    public void registerCommand(String commandName, HashMap<String, Command> commands) throws commandNotExistException {
        String[] cmd = commandName.split(" ");
        if (commands.containsKey(cmd[0]) && cmd.length == 1){
            commandHistory.addFirst(cmd[0]);
            commands.get(cmd[0]).execute();
        }
        else if(commands.containsKey(cmd[0])){
            commandHistory.addFirst(cmd[0]);
            commands.get(cmd[0]).setArgument(cmd[1], CManager.getCommands());
            commands.get(cmd[0]).execute();
        }
        else{
            throw new commandNotExistException();
        }
    }

    public static void stop(){
        stop = false;
    }

    public static ArrayDeque<String> getCommandHistory() {
        return commandHistory;
    }
}


