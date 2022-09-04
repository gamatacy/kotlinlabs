package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.CommandWithArgument;
import commands.ExecutionResult;
import console.User;
import enums.InputMode;
import productClasses.FieldsReader;
import productClasses.Organization;
import productClasses.Product;
import productClasses.ProductBuilder;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Remove all elements by manufacturer value
 */
public class RemoveAllByManufacturerCommand extends Command implements CommandWithArgument {
    private final CollectionManager collectionManager;
    private Integer argument = null;
    private Product product;
    private User user = null;

    public RemoveAllByManufacturerCommand(CollectionManager collectionManager) {
        super("remove_all_by_manufacturer", "удалить из коллекции все элементы, значение поля manufacturer которого эквивалентно заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        Product[] products = collectionManager.getProductsCollection().toArray(new Product[0]);
        ArrayDeque<Product> newDeque = new ArrayDeque<>();

        for (int i = 0; i < collectionManager.getCollectionSize(); i++) {
            if (products[i].getManufacturer().getId() == argument.intValue()) {
                if (products[i].getOwner() != null) {
                    if (!products[i].getOwner().getUsername().equals(this.user.getUsername())) {
                        continue;
                    }
                }
            }
            ProductBuilder.getBuilder().removeId(argument);
            newDeque.addFirst(products[i]);
        }


        if (newDeque.size() == products.length) {
            return ExecutionResult.executionResult(false, "No elements with this manufacturer");
        } else {
            collectionManager.updateCollection(newDeque);
            return ExecutionResult.executionResult(true, "removed");
        }
    }


    @Override
    public void setArgument(ArrayList<Object> argument) {
        try {
            this.argument = Integer.valueOf(String.valueOf(argument.get(0)));
            this.user = (User) argument.get(1);
        } catch (Exception e) {

        }
    }

}
