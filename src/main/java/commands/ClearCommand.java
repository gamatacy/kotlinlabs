package commands;

import collection.CollectionManager;

import java.io.BufferedReader;

/**
 * Delete all elements of collection
 */
public class ClearCommand extends Command{
    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager){
        super("clear","очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Just clear the collection
     * @param reader
     */
    @Override
    public void execute(BufferedReader reader) {
        this.collectionManager.getProductsCollection().clear();
        System.out.println("Collection cleared");
    }

    @Override
    public void setArgument(String arg) {

    }
}
