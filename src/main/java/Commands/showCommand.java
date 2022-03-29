package Commands;

import Collection.Classes.Product;
import Collection.collectionManager;

import java.util.Collection;
import java.util.HashMap;


public class showCommand extends Command{
    private collectionManager cManager;

    public showCommand(collectionManager CM){
        super("show");
        this.cManager = CM;
    }

    @Override
    public void execute() {
        for (Product product : cManager.getProductsCollection()){
            System.out.println(product.toString());
        }
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }
}
