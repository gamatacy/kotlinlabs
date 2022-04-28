package commands;

import productClasses.Product;
import collection.CollectionManager;
import exceptions.CannotBeNullException;
import exceptions.InvalidValueException;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * Update element value by Product ID
 */
public class UpdateCommand extends Command {
    private CollectionManager collectionManager;
    private Integer id;


    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        try {
            ReadElementFromConsole elementReader = new ReadElementFromConsole();
            update(elementReader, reader);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setArgument(String arg) {
        this.id = Integer.parseInt(arg);
    }


    public void update(ReadElementFromConsole elementReader, BufferedReader reader) throws InvalidValueException, CannotBeNullException {

        Product[] productsArray = collectionManager.getProductsCollection().toArray(new Product[0]);

        int flag = 0;

        for (int i = 0; i < productsArray.length; i++) {
            if (productsArray[i].getId().equals(this.id)) {
                productsArray[i] = elementReader.readElement(reader);
                productsArray[i].removeId(this.id);
                productsArray[i].setId(this.id);
                break;
            }
            flag += 1;
        }

        if (flag == productsArray.length){
            throw new InvalidValueException("Product with this ID doesn't exist");
        }

        ArrayDeque<Product> products = new ArrayDeque<>(Arrays.asList(productsArray));

        this.collectionManager.updateCollection(products);

    }

}
