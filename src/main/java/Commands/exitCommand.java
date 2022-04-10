package Commands;

import Console.consoleManager;

import java.io.BufferedReader;
import java.util.HashMap;

public class exitCommand extends Command{

    public exitCommand(){
        super("exit","завершить программу");
    }


    @Override
    public void execute(BufferedReader reader) {
        consoleManager.stop();
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }
}
