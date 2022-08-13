package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.CommandWithArgument;
import commands.ExecutionResult;
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
    private Product argument = null;
    private Product product;

    public RemoveAllByManufacturerCommand(CollectionManager collectionManager) {
        super("remove_all_by_manufacturer", "удалить из коллекции все элементы, значение поля manufacturer которого эквивалентно заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        if(argument == null) {
            FieldsReader fieldsReader = new FieldsReader(Organization.class);
            product = ProductBuilder.getBuilder().buildManufacturer(fieldsReader.read(reader, InputMode.USER));
        }
        else {
            product = argument;
        }

        Product[] products = collectionManager.getProductsCollection().toArray(new Product[0]);
        ArrayDeque<Product> newDeque = new ArrayDeque<>();

        for (int i = 0; i < collectionManager.getCollectionSize(); i++) {
            if (products[i].getManufacturer().equals(product.getManufacturer())) {
                continue;
            } else {
                ProductBuilder.getBuilder().removeId(product.getId());
                newDeque.addFirst(products[i]);
            }
        }


        if (newDeque.size() == products.length) {
            return ExecutionResult.executionResult(false, "No elements with this manufacturer");
        } else {
            collectionManager.updateCollection(newDeque);
            return ExecutionResult.executionResult(true, "Collection updated");
        }
    }


    @Override
    public void setArgument(ArrayList<Object> argument) {
        this.argument = (Product) argument.get(0);
    }

}
