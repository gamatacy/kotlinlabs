package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.CommandWithArgument;
import commands.ExecutionResult;
import enums.InputMode;
import productClasses.FieldsReader;
import productClasses.Product;
import productClasses.ProductBuilder;

import java.io.BufferedReader;

/**
 * Add element to collection from user input
 */
public class AddCommand extends Command implements CommandWithArgument {
    private final CollectionManager collectionManager;
    private InputMode inputMode = InputMode.USER;
    private Product argument = null;

    public AddCommand(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResult execute(BufferedReader reader) {
        if (argument == null) {
            try {
                FieldsReader fieldsReader = new FieldsReader(Product.class);
                Product product = ProductBuilder.getBuilder().buildProduct(fieldsReader.read(reader, inputMode));
                collectionManager.addToCollectionFirst(product);
                this.inputMode = InputMode.USER;
                return ExecutionResult.executionResult(true, "Element added");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            collectionManager.addToCollectionFirst(argument);
            this.inputMode = InputMode.USER;
            argument = null;
            return ExecutionResult.executionResult(true, "Element added");
        }
        return ExecutionResult.executionResult(false, "Element not added");
    }


    @Override
    public void setArgument(Object argument) {
        this.argument = (Product) argument;
        this.inputMode = InputMode.SCRIPT;
    }

}
