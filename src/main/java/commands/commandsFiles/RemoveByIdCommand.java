package commands.commandsFiles;

import commands.Command;
import commands.CommandWithArgument;
import commands.ExecutionResult;
import productClasses.Product;
import collection.CollectionManager;
import productClasses.ProductBuilder;

import java.io.BufferedReader;
import java.util.ArrayDeque;

/**
 * Remove one element by id value
 */
public class RemoveByIdCommand extends Command implements CommandWithArgument {
    private final int argumentsCount = 1;
    private final CollectionManager collectionManager;
    private Integer id;


    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", " удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        return remove();
    }

    public ExecutionResult remove() {
        Product[] array = collectionManager.getProductsCollection().toArray(new Product[0]);
        ArrayDeque<Product> updatedDeque = new ArrayDeque<>();

        for (Product product : array) {
            if (product.getId().equals(id) == false) {
                ProductBuilder.getBuilder().removeId(product.getId());
                updatedDeque.addLast(product);
            }
        }

        if (array.length == updatedDeque.size()) {
            return ExecutionResult.executionResult(false, "Product with this ID doesn't exist");
        }

        this.collectionManager.updateCollection(updatedDeque);
        return ExecutionResult.executionResult(true, "Element removed");
    }

    @Override
    public void setArgument(String[] args) {
        try {
            this.id = Integer.valueOf(args[0]);
        } catch (Exception e) {
            this.id = null;
        }
    }

    @Override
    public int getArgumentsCount() {
        return this.argumentsCount;
    }
}
