package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.ExecutionResult;
import productClasses.ProductBuilder;

import java.io.BufferedReader;

/**
 * Delete all elements of collection
 */
public class ClearCommand extends Command {
    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        this.collectionManager.getProductsCollection().clear();
        ProductBuilder.getBuilder().clearId();
        return ExecutionResult.executionResult(true, "Collection cleared");
    }

}
