package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.CommandWithArgument;
import commands.ExecutionResult;
import console.User;
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
    private User user = null;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "delete element by id");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        if (this.id == null) {
            return ExecutionResult.executionResult(false, "Must be int!");
        }

        Product[] array = collectionManager.getProductsCollection().toArray(new Product[0]);
        ArrayDeque<Product> updatedDeque = new ArrayDeque<>();

        for (Product product : array) {
            if (product.getId().intValue() != this.id.intValue()) {
                updatedDeque.addLast(product);
            } else {
                if (product.getOwner() != null) {
                    if (!(product.getOwner().getUsername().equals(this.user.getUsername()))) {
                        return ExecutionResult.executionResult(false, "You do not have access to this product!");
                    }
                }
                ProductBuilder.getBuilder().removeId(this.id);
            }
        }

        if (array.length == updatedDeque.size()) {
            return ExecutionResult.executionResult(false, "Product with this ID doesn't exist");
        }


        this.collectionManager.updateCollection(updatedDeque);
        return ExecutionResult.executionResult(true, "removed");
    }

    @Override
    public void setArgument(ArrayList<Object> argument) {
        this.id = null;
        this.user = null;
        try {
            this.id = Integer.valueOf(argument.get(0).toString());
            this.user = (User) argument.get(1);
        } catch (Exception e) {

        }
    }
}
