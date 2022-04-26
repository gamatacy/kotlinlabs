package commands;

import productClasses.Product;
import collection.CollectionManager;

import java.io.BufferedReader;
import java.util.HashMap;


/**
 * Print all collection elements in String
 */
public class ShowCommand extends Command{
    private CollectionManager cManager;

    public ShowCommand(CollectionManager CM){
        super("show","вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.cManager = CM;
    }

    @Override
    public void execute(BufferedReader reader) {
        for (Product product : cManager.getProductsCollection()){
            System.out.println(product.toString());
        }
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }
}
