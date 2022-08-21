package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.CommandWithArgument;
import commands.ExecutionResult;
import enums.InputMode;
import productClasses.FieldsReader;
import productClasses.Product;
import productClasses.ProductBuilder;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Update element value by Product ID
 */
public class UpdateCommand extends Command implements CommandWithArgument {
    private CollectionManager collectionManager;
    private Integer id = null;
    private Product product = null;

    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        Product[] products = collectionManager.getProductsCollection().toArray(new Product[0]);
        if(this.id == null){
            return ExecutionResult.executionResult(false, "Id must be int!");
        }
        if (product == null) {
            for (int i = 0; i < products.length; i++) {
                if (products[i].getId().intValue() == this.id.intValue()) {
                    FieldsReader fieldsReader = new FieldsReader(Product.class);
                    ProductBuilder.getBuilder().removeId(this.id);
                    product = ProductBuilder.getBuilder().buildProduct(fieldsReader.read(reader, InputMode.USER));
                    product.setId(this.id);
                    products[i] = product;
                    ArrayDeque<Product> updatedDeque = new ArrayDeque<>(Arrays.asList(products));
                    collectionManager.updateCollection(updatedDeque);
                    id = null;
                    product = null;
                    return ExecutionResult.executionResult(true, "Element updated");
                }
            }
        }else {
            for (int i = 0; i < products.length; i++) {
                if (products[i].getId().intValue() == this.id.intValue()) {
                    product.setId(this.id);
                    products[i] = product;
                    ArrayDeque<Product> updatedDeque = new ArrayDeque<>(Arrays.asList(products));
                    collectionManager.updateCollection(updatedDeque);
                    id = null;
                    product = null;
                    return ExecutionResult.executionResult(true, "Element updated");
                }
            }
        }

        return ExecutionResult.executionResult(false, "No element with this id");
    }

    @Override
    public void setArgument(ArrayList<Object> argument) {
        try {
            this.id = Integer.valueOf(argument.get(0).toString());
            this.product = (Product) argument.get(1);
        } catch (Exception e) {}
    }
}
