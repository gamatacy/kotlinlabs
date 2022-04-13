package Commands;

import Collection.Classes.Organization;
import Collection.Classes.Product;
import Collection.collectionManager;
import Exceptions.cannotBeNullException;
import Exceptions.invalidValueException;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Remove all elements by manufacturer value
 */
public class removeAllByManufacturerCommand extends Command{
    private collectionManager cManager;


    public removeAllByManufacturerCommand(collectionManager cManager){
        super("remove_all_by_manufacturer","удалить из коллекции все элементы, значение поля manufacturer которого эквивалентно заданному");
        this.cManager = cManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        Product product = new Product();

        readElementFromConsole readConsole = new readElementFromConsole();

        readConsole.setOrganization(reader, product);

        try {
            remove(product);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {

    }

    public void remove(Product product) throws invalidValueException, cannotBeNullException {
        Product[] array = cManager.getProductsCollection().toArray(new Product[0]);
        ArrayDeque<Product> newCollection = new ArrayDeque<>();

        for (int i = 0; i < array.length ;i++){
            if (array[i].getManufacturer().equals(product.getManufacturer())==false){
                newCollection.addLast(array[i]);
            }
        }

        this.cManager.updateCollection(newCollection);

    }

}
