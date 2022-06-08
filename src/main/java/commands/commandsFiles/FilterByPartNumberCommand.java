package commands.commandsFiles;

import collection.*;
import commands.Command;
import commands.ExecutionResult;
import productClasses.Product;

import java.io.BufferedReader;


/**
 * Print elements by specified partNumber
 */
public class FilterByPartNumberCommand extends Command {
    private CollectionManager collectionManager;

    public FilterByPartNumberCommand(CollectionManager collectionManager) {
        super("filter_by_part_number", "вывести элементы, значение поля partNumber которых равно заданному");
        this.collectionManager = collectionManager;
    }


    @Override
    public ExecutionResult execute(BufferedReader reader) {
        Product[] products = collectionManager.getProductsCollection().toArray(new Product[0]);
        String result = "\n";

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

        return ExecutionResult.executionResult(true, result);
    }


}
