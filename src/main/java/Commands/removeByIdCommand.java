package Commands;

import Collection.Classes.Product;
import Collection.collectionManager;
import Exceptions.cannotBeNullException;
import Exceptions.invalidValueException;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashMap;

public class removeByIdCommand extends Command{
    private collectionManager cManager;
    private Integer id;


    public removeByIdCommand(collectionManager cManager){
        super("remove_by_id"," удалить элемент из коллекции по его id");
        this.cManager = cManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        try {
            remove();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {
        this.id = Integer.parseInt(arg);
    }

    public void remove() throws invalidValueException{
        Product[] array = cManager.getProductsCollection().toArray(new Product[0]);
        ArrayDeque<Product> newCollection = new ArrayDeque<>();


        for (Product product : array) {
            if (product.getId().equals(id) == false) {
                newCollection.addLast(product);
            }
        }

        if(array.length == newCollection.size()){
            throw new invalidValueException("Product with this ID doesn't exist");
        }

        //ArrayDeque<Product> newCollection = new ArrayDeque<Product>(Arrays.asList(array));

        this.cManager.updateCollection(newCollection);
    }
}
