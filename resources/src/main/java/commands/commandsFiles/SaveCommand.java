package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.CommandWithArgument;
import commands.ExecutionResult;
import utils.FileManager;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Save collection into csv file, standart output is test.csv
 */
public class SaveCommand extends Command implements CommandWithArgument {
    private final int argumentsCount = 1;
    private final CollectionManager collectionManager;
    private final FileManager fileManager;
    private String path;

    public SaveCommand(CollectionManager collectionManager, FileManager fileManager) {
        super("save", " сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        try {
            fileManager.saveFile(this.path, this.collectionManager.getProductsCollection());
            return ExecutionResult.executionResult(true, "Collection saved");
        } catch (Exception e) {
            return ExecutionResult.executionResult(false, "Failed to saved collection");
        }
    }

    @Override
    public void setArgument(ArrayList<Object> argument) {
        try {
            this.path = (String) argument.get(0);
        }catch (Exception e){
            this.path = "collection.csv";
        }
    }
}
