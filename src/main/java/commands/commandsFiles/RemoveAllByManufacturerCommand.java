package commands.commandsFiles;

import commands.Command;
import commands.ExecutionResult;
import enums.InputMode;
import productClasses.FieldsReader;
import productClasses.Organization;
import productClasses.Product;
import collection.CollectionManager;
import productClasses.ProductBuilder;

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
    public ExecutionResult execute(BufferedReader reader) {
        FieldsReader fieldsReader = new FieldsReader(Organization.class);
        Product product = ProductBuilder.getBuilder().buildManufacturer(fieldsReader.read(reader, InputMode.USER));
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


}
