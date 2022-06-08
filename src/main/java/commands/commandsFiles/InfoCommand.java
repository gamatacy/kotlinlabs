package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.ExecutionResult;

import java.io.BufferedReader;

/**
 * Print info about the collection
 */
public class InfoCommand extends Command {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        String info = "\n" +
                "Тип коллекции: " + collectionManager.getProductsCollection().getClass() + "\n" +
                "Дата инициализации: " + collectionManager.getCreationDate() + "\n" +
                "Количество элементов: " + collectionManager.getCollectionSize() + "\n";
        return ExecutionResult.executionResult(true, info);
    }

}
