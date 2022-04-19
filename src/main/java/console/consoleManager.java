package console;


import commands.Command;
import commands.commandManager;
import commands.registerCommand;
import exceptions.commandNotExistException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Read input from user and register commands
 */
public class consoleManager implements registerCommand {
    private static String USERNAME = "user";
    private commandManager CManager;
    private static ArrayDeque<String> commandHistory = new ArrayDeque<>();
    private BufferedReader reader;

    public consoleManager(commandManager commandmanager, BufferedReader reader){
        this.CManager = commandmanager;
        this.reader = reader;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    private static boolean stop = true;

    private static boolean scriptInput = false;

    public void run() throws IOException {
        while(stop) {
            System.out.print("<"+this.USERNAME+">$");

            //  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {

                String command = reader.readLine();

                registerCommand(command, CManager.getCommands());

                scriptInput = false;

            } catch (Exception e) {

                System.out.println(e.getMessage());

            }
        }

        try{reader.close();}catch (Exception e){e.printStackTrace();}

    }

    public void registerCommand(String commandName, HashMap<String, Command> commands) throws commandNotExistException {
        String[] cmd = commandName.split(" ");
        if (commands.containsKey(cmd[0]) && cmd.length == 1){
            commandHistory.addFirst(cmd[0]);
            commands.get(cmd[0]).execute(this.reader);
        }
        else if(commands.containsKey(cmd[0])){
            commandHistory.addFirst(cmd[0]);
            commands.get(cmd[0]).setArgument(cmd[1], CManager.getCommands());
            commands.get(cmd[0]).execute(this.reader);
        }
        else{
            throw new commandNotExistException();
        }
    }

    public static void stop(){
        stop = false;
    }

    public static void setScriptInput(){
        scriptInput = true;
    }

    public static boolean getScriptInput(){
        return scriptInput;
    }

    public static ArrayDeque<String> getCommandHistory() {
        return commandHistory;
    }
}


