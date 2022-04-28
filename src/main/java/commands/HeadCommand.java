package commands;

import collection.CollectionManager;

import java.io.BufferedReader;

/**
 *Print first element of the collection
 */
public class HeadCommand extends Command{
    private final CollectionManager collectionManager;


    public HeadCommand(CollectionManager collectionManager){
        super("head","вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        System.out.println(collectionManager.getCollectionFirst());
    }

    @Override
    public void setArgument(String arg) {

    }
}
