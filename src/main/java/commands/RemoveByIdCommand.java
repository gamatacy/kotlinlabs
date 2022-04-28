package commands;

import productClasses.Product;
import collection.CollectionManager;
import exceptions.InvalidValueException;

import java.io.BufferedReader;
import java.util.ArrayDeque;

/**
 * Remove one element by id value
 */
public class RemoveByIdCommand extends Command {
    private final CollectionManager collectionManager;
    private Integer id;


    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", " удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        try {
            remove();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setArgument(String arg) {
        this.id = Integer.parseInt(arg);
    }

    public void remove() throws InvalidValueException {
        Product[] array = collectionManager.getProductsCollection().toArray(new Product[0]);
        ArrayDeque<Product> newCollection = new ArrayDeque<>();

        for (Product product : array) {
            if (product.getId().equals(id) == false) {
                newCollection.addLast(product);
            }
        }

        if (array.length == newCollection.size()) {
            throw new InvalidValueException("Product with this ID doesn't exist");
        }

        this.collectionManager.updateCollection(newCollection);
    }
}
