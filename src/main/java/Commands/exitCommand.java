package Commands;

import Console.consoleManager;

import java.util.HashMap;

public class exitCommand extends Command{

    public exitCommand(){
        super("exit");
    }


    @Override
    public void execute() {
        consoleManager.stop();
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }
}
