package Commands;

import Collection.Classes.Product;
import Collection.collectionManager;

import java.util.Collection;


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
}
