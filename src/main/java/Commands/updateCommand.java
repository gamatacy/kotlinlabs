package Commands;

import Collection.Classes.Product;
import Collection.collectionManager;
import Exceptions.cannotBeNullException;
import Exceptions.invalidValueException;

import java.io.BufferedReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

public class updateCommand extends Command{
    private collectionManager cManager;
    private Integer id;


    public updateCommand(collectionManager cManager){
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.cManager = cManager;
    }

    @Override
    public void execute(BufferedReader reader) {
        try {
            readElementFromConsole elementReader = new readElementFromConsole();
            update(elementReader, reader);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {
        this.id = Integer.parseInt(arg);
    }


    public void update(readElementFromConsole elementReader, BufferedReader reader) throws invalidValueException, cannotBeNullException {
        Product[] array = cManager.getProductsCollection().toArray(new Product[0]);

        for (int i = 0; i < array.length ;i++){
            System.out.println(array[i].getId());
            if (array[i].getId().equals(this.id)){
                array[i] = elementReader.readElement(reader);
                array[i].removeId(this.id);
                array[i].setId(this.id);
                break;
            }
            if(i== array.length) {
                throw new invalidValueException("Product with this ID doesn't exist");
            }
        }

        ArrayDeque<Product> newCollection = new ArrayDeque<Product>(Arrays.asList(array));

        this.cManager.updateCollection(newCollection);

    }

}
