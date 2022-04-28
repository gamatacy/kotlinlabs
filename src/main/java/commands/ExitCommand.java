package commands;

import console.ConsoleManager;

import java.io.BufferedReader;

/**
 * Stop the programm
 */
public class ExitCommand extends Command{


    public ExitCommand(){
        super("exit","завершить программу");
    }


    @Override
    public void execute(BufferedReader reader) {
        ConsoleManager.stop();
    }

    @Override
    public void setArgument(String arg) {

    }
}
