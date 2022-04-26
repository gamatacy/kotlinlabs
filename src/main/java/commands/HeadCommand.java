package commands;

import collection.CollectionManager;

import java.io.BufferedReader;
import java.util.HashMap;

/**
 *Print first element of the collection
 */
public class HeadCommand extends Command{
    private CollectionManager cManager;


    public HeadCommand(CollectionManager cManager){
        super("head","вывести первый элемент коллекции");
        this.cManager = cManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        System.out.println(cManager.getCollectionFirst());
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }
}
