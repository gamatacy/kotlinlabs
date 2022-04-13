package Commands;

import Collection.collectionManager;

import java.io.BufferedReader;
import java.util.HashMap;

/**
 *Print first element of the collection
 */
public class headCommand extends Command{
    private collectionManager cManager;


    public headCommand(collectionManager cManager){
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
