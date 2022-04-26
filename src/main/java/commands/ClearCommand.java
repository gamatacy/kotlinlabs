package commands;

import collection.CollectionManager;

import java.io.BufferedReader;
import java.util.HashMap;

/**
 * Delete all elements of collection
 */
public class ClearCommand extends Command{
    private CollectionManager cManager;

    public ClearCommand(CollectionManager cManager){
        super("clear","очистить коллекцию");
        this.cManager = cManager;
    }

    /**
     * Just clear the collection
     * @param reader
     */
    @Override
    public void execute(BufferedReader reader) {
        this.cManager.getProductsCollection().clear();
        System.out.println("Collection cleared");
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }
}
