package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.ExecutionResult;
import productClasses.Product;
import productClasses.ProductBuilder;

import java.io.BufferedReader;

/**
 * Print first element and delete him
 */
public class RemoveHeadCommand extends Command {
    private final CollectionManager collectionManager;


    public RemoveHeadCommand(CollectionManager collectionManager) {
        super("remove_head", "вывести первый элемент коллекции и удалить его");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        try {
            Product product = collectionManager.removeFirst();
            ProductBuilder.getBuilder().removeId(product.getId());
            System.out.println(product.toString());
            return ExecutionResult.executionResult(true, "Element removed");
        } catch (Exception e) {
            return ExecutionResult.executionResult(false, "Collection is empty");
        }
    }

}
