package commands;

import productClasses.Product;
import collection.CollectionManager;

import java.io.BufferedReader;
import java.util.TreeSet;

/**
 * Print elements sorted by id
 */
public class PrintAscendingCommand extends Command {
    private CollectionManager collectionManager;

    public PrintAscendingCommand(CollectionManager collectionManager) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        TreeSet<Product> productTreeSet = new TreeSet<>(collectionManager.getProductsCollection());

        for (Product product : productTreeSet) {
            System.out.println(product.toString());
        }

    }

    @Override
    public void setArgument(String arg) {

    }

}
