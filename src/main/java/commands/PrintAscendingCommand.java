package commands;

import productClasses.Product;
import collection.CollectionManager;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Print elements sorted by id
 */
public class PrintAscendingCommand extends Command{
    private CollectionManager cManager;

    public PrintAscendingCommand(CollectionManager cManager){
        super("print_ascending","вывести элементы коллекции в порядке возрастания");
        this.cManager = cManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        sort();
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }

    public void sort(){
        TreeSet<Product> treset = new TreeSet<>(cManager.getProductsCollection());

        for(Product product: treset){
            System.out.println(product.toString());
        }

    }

}