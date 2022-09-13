package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.ExecutionResult;

import java.io.BufferedReader;

/**
 * Print first element of the collection
 */
public class HeadCommand extends Command {
    private final CollectionManager collectionManager;


    public HeadCommand(CollectionManager collectionManager) {
        super("head", "print first element");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        try {
            return ExecutionResult.executionResult(true, collectionManager.getCollectionFirst().toString());
        } catch (Exception e) {
            return ExecutionResult.executionResult(false, "Collection is empty");
        }
    }

}
