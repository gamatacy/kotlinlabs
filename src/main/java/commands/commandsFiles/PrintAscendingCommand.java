package commands.commandsFiles;

import commands.Command;
import commands.ExecutionResult;
import productClasses.Product;
import collection.CollectionManager;

import java.io.BufferedReader;
import java.util.TreeSet;

/**
 * Print elements sorted by id
 */
public class PrintAscendingCommand extends Command {
    private CollectionManager collectionManager;

    public PrintAscendingCommand(CollectionManager collectionManager) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        TreeSet<Product> productTreeSet = new TreeSet<>(collectionManager.getProductsCollection());
        if (productTreeSet.size() == 0) {
            return ExecutionResult.executionResult(false, "Collection is empty");
        }
        String result = "\n";
        for (Product product : productTreeSet) {
            result += product + "\n";
        }
        return ExecutionResult.executionResult(true, result);
    }

}
