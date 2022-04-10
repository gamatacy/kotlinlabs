package Commands;

import Console.*;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.HashMap;

public class historyCommand extends Command{
    private ArrayDeque history;


    public historyCommand(ArrayDeque<String> array){
        super("history","вывести последние 8 команд");
        this.history = array;
    }

    @Override
    public void execute(BufferedReader reader) {

        ArrayDeque<String> copyArray = new ArrayDeque<>(history);

        if (copyArray.size() < 8){
            for(int i = 0; i <= copyArray.size(); i++){
                System.out.println(copyArray.removeLast());
            }
        }
        else {
            for (int i = 0; copyArray.size() != 8; i++){
                copyArray.removeLast();
            }
            for (int i = 0; i < 8; i++){
                System.out.println(copyArray.removeLast());
            }
        }
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {}
}
