package commands.commandsFiles;

import collection.CollectionManager;
import commands.Command;
import commands.CommandWithArgument;
import commands.ExecutionResult;
import productClasses.Product;

import java.io.BufferedReader;
import java.util.ArrayList;


/**
 * Print elements by specified partNumber
 */
public class FilterByPartNumberCommand extends Command implements CommandWithArgument {
    private CollectionManager collectionManager;
    private String argument = null;

    public FilterByPartNumberCommand(CollectionManager collectionManager) {
        super("filter_by_part_number", "вывести элементы, значение поля partNumber которых равно заданному");
        this.collectionManager = collectionManager;
    }


    @Override
    public ExecutionResult execute(BufferedReader reader) {
        Product[] products = collectionManager.getProductsCollection().toArray(new Product[0]);
        String result = "\n";

        if (argument != null) {
            String partNumber = argument;
            for (int i = 0; i < products.length; i++) {
                if (products[i].getPartNumber().equals(partNumber)) {
                    result += products[i] + "\n";
                }
            }
        } else {
            try {
                System.out.print("Введите partNumber: ");
                String partNumber = reader.readLine();
                for (int i = 0; i < products.length; i++) {
                    if (products[i].getPartNumber().equals(partNumber)) {
                        result += products[i] + "\n";
                    }
                }
            } catch (Exception e) {
                return ExecutionResult.executionResult(false, "Reading failed");
            }
        }

        argument = null;

        return ExecutionResult.executionResult(true, result);
    }


    @Override
    public void setArgument(ArrayList<Object> argument) {
        this.argument = argument.get(0).toString();
    }
}
