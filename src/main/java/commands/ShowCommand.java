package commands;

import productClasses.Product;
import collection.CollectionManager;

import java.io.BufferedReader;


/**
 * Print all collection elements in String
 */
public class ShowCommand extends Command {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        for (Product product : collectionManager.getProductsCollection()) {
            System.out.println(product.toString());
        }
    }

    @Override
    public void setArgument(String arg) {

    }
}
