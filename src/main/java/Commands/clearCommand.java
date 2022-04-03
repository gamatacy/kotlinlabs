package Commands;

import Collection.Classes.Product;
import Collection.collectionManager;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.HashMap;

public class clearCommand extends Command{
    private collectionManager cManager;

    public clearCommand(collectionManager cManager){
        super("clear");
        this.cManager = cManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        this.cManager.getProductsCollection().clear();
        System.out.println("Collection cleared");
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }
}
