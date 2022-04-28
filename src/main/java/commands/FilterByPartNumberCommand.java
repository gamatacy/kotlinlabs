package commands;

import collection.*;
import productClasses.Product;

import java.io.BufferedReader;
import java.util.ArrayDeque;

/**
 *Print elements by specified partNumber
 */
public class FilterByPartNumberCommand extends Command{
    private CollectionManager collectionManager;
    private String partNumber;


    public FilterByPartNumberCommand(CollectionManager collectionManager){
        super("filter_by_part_number","вывести элементы, значение поля partNumber которых равно заданному");
        this.collectionManager = collectionManager;
    }


    @Override
    public void execute(BufferedReader reader) {
        ArrayDeque<Product> products = new ArrayDeque<>(collectionManager.getProductsCollection());

        for ( Product product: products){
            if (product.getPartNumber().equals(this.partNumber)){
                System.out.println(products.pop());
            }
            else{
                products.pop();
            }
        }
    }

    @Override
    public void setArgument(String arg) {
        this.partNumber = arg;
    }


}
