package commands;

import productClasses.Product;
import collection.CollectionManager;
import exceptions.CannotBeNullException;
import exceptions.InvalidValueException;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Update element value by Product ID
 */
public class UpdateCommand extends Command{
    private CollectionManager cManager;
    private Integer id;


    public UpdateCommand(CollectionManager cManager){
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.cManager = cManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        try {
            ReadElementFromConsole elementReader = new ReadElementFromConsole();
            update(elementReader, reader);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {
        this.id = Integer.parseInt(arg);
    }


    public void update(ReadElementFromConsole elementReader, BufferedReader reader) throws InvalidValueException, CannotBeNullException {
        Product[] array = cManager.getProductsCollection().toArray(new Product[0]);

        for (int i = 0; i < array.length ;i++){
            if (array[i].getId().equals(this.id)){
                array[i] = elementReader.readElement(reader);
                array[i].removeId(this.id);
                array[i].setId(this.id);
                break;
            }
            if(i== array.length) {
                throw new InvalidValueException("Product with this ID doesn't exist");
            }
        }

        ArrayDeque<Product> newCollection = new ArrayDeque<Product>(Arrays.asList(array));

        this.cManager.updateCollection(newCollection);

    }

}
