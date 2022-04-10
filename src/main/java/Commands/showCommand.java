package Commands;

import Collection.Classes.Product;
import Collection.collectionManager;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.HashMap;


public class showCommand extends Command{
    private collectionManager cManager;

    public showCommand(collectionManager CM){
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
