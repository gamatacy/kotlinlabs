package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.CommandWithArgument;
import commands.ExecutionResult;
import productClasses.Product;
import productClasses.ProductBuilder;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Remove one element by id value
 */
public class RemoveByIdCommand extends Command implements CommandWithArgument {
    private final CollectionManager collectionManager;
    private Integer id = null;


    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", " удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        if (this.id == null){
            return ExecutionResult.executionResult(false, "Must be int!");
        }

        Product[] array = collectionManager.getProductsCollection().toArray(new Product[0]);
        ArrayDeque<Product> updatedDeque = new ArrayDeque<>();

        for (Product product : array) {
            if (product.getId().intValue() != this.id.intValue()) {
                updatedDeque.addLast(product);
            }else{
                ProductBuilder.getBuilder().removeId(this.id);
            }
        }

        if (array.length == updatedDeque.size()) {
            return ExecutionResult.executionResult(false, "Product with this ID doesn't exist");
        }

        this.id = null;
        this.collectionManager.updateCollection(updatedDeque);
        return ExecutionResult.executionResult(true, "removed");
    }

    @Override
    public void setArgument(ArrayList<Object> argument) {
        try {
            this.id = Integer.valueOf(argument.get(0).toString());
        } catch (Exception e) {
            this.id = null;
        }
    }
}
