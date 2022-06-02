package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.CommandWithArgument;
import commands.ExecutionResult;
import enums.InputMode;
import productClasses.*;

import java.io.BufferedReader;

/**
 * Add element to collection from user input
 */
public class AddCommand extends Command implements CommandWithArgument {
    private final int argumentsCount = 0;
    private final CollectionManager collectionManager;
    private InputMode inputMode = InputMode.USER;

    public AddCommand(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * @param reader
     * @return
     */
    @Override
    public ExecutionResult execute(BufferedReader reader) {
        try {
            FieldsReader fieldsReader = new FieldsReader(Product.class);
            Product product = ProductBuilder.getBuilder().buildProduct(fieldsReader.read(reader, inputMode));
            collectionManager.addToCollectionFirst(product);
            this.inputMode = InputMode.USER;
            return ExecutionResult.executionResult(true, "Element added");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExecutionResult.executionResult(false, "Element not added");
    }

    @Override
    public void setArgument(String[] args) {
        if (args.length == 2) {
            this.inputMode = InputMode.SCRIPT;
        }
    }

    @Override
    public int getArgumentsCount() {
        return this.argumentsCount ;
    }
}
