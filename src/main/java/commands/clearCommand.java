package Commands;

import Collection.Classes.Product;
import Collection.collectionManager;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Delete all elements of collection
 */
public class clearCommand extends Command{
    private collectionManager cManager;

    public clearCommand(collectionManager cManager){
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
