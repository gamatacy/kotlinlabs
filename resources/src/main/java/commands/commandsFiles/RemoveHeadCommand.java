package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.CommandWithArgument;
import commands.ExecutionResult;
import console.User;
import productClasses.Product;
import productClasses.ProductBuilder;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Print first element and delete him
 */
public class RemoveHeadCommand extends Command implements CommandWithArgument {
    private final CollectionManager collectionManager;
    private User user = null;

    public RemoveHeadCommand(CollectionManager collectionManager) {
        super("remove_head", "print and delete first element");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        try {
            if (collectionManager.getProductsCollection().size() == 0) {
                return ExecutionResult.executionResult(false, "Collection is empty");
            }

            if (collectionManager.getCollectionFirst().getOwner() != null) {
                if (!(collectionManager.getCollectionFirst()).getOwner().getUsername().equals(this.user.getUsername())) {
                    return ExecutionResult.executionResult(false, "You do not have access to this product!");
                }
            }
            Product product = collectionManager.removeFirst();
            ProductBuilder.getBuilder().removeId(product.getId());
            System.out.println(product.toString());
            return ExecutionResult.executionResult(true, "removed");
        } catch (Exception e) {
            return ExecutionResult.executionResult(false, "You do not have access to this product!");
        }
    }

    @Override
    public void setArgument(ArrayList<Object> argument) {
        this.user = null;
        if (argument.size() > 0) {
            this.user = (User) argument.get(0);
        }
    }
}
