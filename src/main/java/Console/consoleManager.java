package Console;


import Commands.commandManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class consoleManager {
    private commandManager CManager;

    public consoleManager(commandManager commandmanager){
        this.CManager = commandmanager;
    }

    public void run() throws IOException {
            System.out.print("<user>$ ");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try{
                CManager.registerCommand(reader.readLine());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            finally {
                run();
            }
        }
}


