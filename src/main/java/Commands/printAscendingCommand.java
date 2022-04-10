package Commands;

import Collection.Classes.Product;
import Collection.collectionManager;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.TreeSet;

public class printAscendingCommand extends Command{
    private collectionManager cManager;

    public printAscendingCommand(collectionManager cManager){
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
