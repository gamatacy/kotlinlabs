package commands;

import collection.CollectionManager;

import java.io.BufferedReader;

/**
 * Print first element and delete him
 */
public class RemoveHeadCommand extends Command{
    private final CollectionManager collectionManager;


    public RemoveHeadCommand(CollectionManager collectionManager){
        super("remove_head","вывести первый элемент коллекции и удалить его");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        System.out.println(collectionManager.getProductsCollection().pop());
    }

    @Override
    public void setArgument(String arg) {

    }
}
