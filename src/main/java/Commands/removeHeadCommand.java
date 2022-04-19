package Commands;

import Collection.collectionManager;

import java.io.BufferedReader;
import java.util.HashMap;

/**
 * Print first element and delete him
 */
public class removeHeadCommand extends Command{
    private collectionManager cManager;


    public removeHeadCommand(collectionManager cManager){
        super("remove_head","вывести первый элемент коллекции и удалить его");
        this.cManager = cManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        System.out.println(cManager.getProductsCollection().pop());
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }
}