package commands.commandsFiles;

import commands.Command;
import commands.CommandWithArgument;
import commands.ExecutionResult;
import enums.InputMode;
import productClasses.FieldsReader;
import productClasses.Product;
import collection.CollectionManager;

import productClasses.ProductBuilder;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.Arrays;


/**
 * Update element value by Product ID
 */
public class UpdateCommand extends Command implements CommandWithArgument {
    private final int argumentsCount = 1;
    private CollectionManager collectionManager;
    private Integer id;


    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        return update(reader);
    }

    private ExecutionResult update(BufferedReader reader) {
        Product[] products = collectionManager.getProductsCollection().toArray(new Product[0]);
        for (int i = 0; i < products.length; i++) {
            if (products[i].getId() == this.id) {
                FieldsReader fieldsReader = new FieldsReader(Product.class);
                ProductBuilder.getBuilder().removeId(this.id);
                products[i] = ProductBuilder.getBuilder().buildProduct(fieldsReader.read(reader, InputMode.USER));
                ArrayDeque<Product> updatedDeque = new ArrayDeque<>(Arrays.asList(products));
                collectionManager.updateCollection(updatedDeque);
                return ExecutionResult.executionResult(true, "Element updated");
            }
        }
        return ExecutionResult.executionResult(false, "No element with this id");
    }

    @Override
    public void setArgument(String[] args) {
        try{
            this.id = Integer.valueOf(args[0]);
        }catch (Exception e){
            this.id = null;
        }
    }

    @Override
    public int getArgumentsCount() {
        return this.argumentsCount;
    }
}
