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
import java.util.ArrayList;

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
        } else {
            argument.setId(ProductBuilder.getBuilder().idGenerator());
            collectionManager.addToCollectionFirst(argument);
            this.inputMode = InputMode.USER;
            argument = null;
            return ExecutionResult.executionResult(true, "Element added");
        }
        return ExecutionResult.executionResult(false, "Element not added");
    }


    @Override
    public void setArgument(ArrayList<Object> argument) {
        this.inputMode = InputMode.SCRIPT;
        try {
            this.argument = (Product) argument.get(0);
        } catch (Exception e) {
            this.argument = null;
        }
    }

    public void setScriptMode(){
        this.inputMode = InputMode.SCRIPT;
    }

}
