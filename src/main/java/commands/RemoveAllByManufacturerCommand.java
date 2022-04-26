package commands;

import productClasses.Product;
import collection.CollectionManager;
import exceptions.CannotBeNullException;
import exceptions.InvalidValueException;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Remove all elements by manufacturer value
 */
public class RemoveAllByManufacturerCommand extends Command{
    private CollectionManager cManager;


    public RemoveAllByManufacturerCommand(CollectionManager cManager){
        super("remove_all_by_manufacturer","удалить из коллекции все элементы, значение поля manufacturer которого эквивалентно заданному");
        this.cManager = cManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        Product product = new Product();

        ReadElementFromConsole readConsole = new ReadElementFromConsole();

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

    public void remove(Product product) throws InvalidValueException, CannotBeNullException {
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
