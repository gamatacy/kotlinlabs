package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.ExecutionResult;
import productClasses.Product;

import java.io.BufferedReader;


/**
 * Print all collection elements in String
 */
public class ShowCommand extends Command {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", "print all elements");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        if (collectionManager.getProductsCollection().size() != 0) {
            String products = "\n";
            for (Product product : collectionManager.getProductsCollection()) {
                products += product + "\n";
            }
            return ExecutionResult.executionResult(true, products);
        } else {
            return ExecutionResult.executionResult(true, "Collection is empty");
        }
    }


}
