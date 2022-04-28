package commands;

import productClasses.Product;
import collection.CollectionManager;

import java.io.BufferedReader;
import java.util.ArrayDeque;

/**
 * Remove all elements by manufacturer value
 */
public class RemoveAllByManufacturerCommand extends Command {
    private final CollectionManager collectionManager;


    public RemoveAllByManufacturerCommand(CollectionManager collectionManager) {
        super("remove_all_by_manufacturer", "удалить из коллекции все элементы, значение поля manufacturer которого эквивалентно заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        Product product = new Product();

        ReadElementFromConsole readConsole = new ReadElementFromConsole();

        readConsole.setOrganization(reader, product);

        try {
            remove(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void setArgument(String arg) {

    }

    public void remove(Product product) {
        Product[] array = collectionManager.getProductsCollection().toArray(new Product[0]);
        ArrayDeque<Product> newCollection = new ArrayDeque<>();

        for (int i = 0; i < array.length; i++) {
            if (!array[i].getManufacturer().equals(product.getManufacturer())) {
                newCollection.addLast(array[i]);
            }
        }

        this.collectionManager.updateCollection(newCollection);

    }

}
