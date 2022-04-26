package commands;

import collection.*;
import productClasses.Product;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.HashMap;

/**
 *Print elements by specified partNumber
 */
public class FilterByPartNumberCommand extends Command{
    private CollectionManager cManager;
    private String partNumber;


    public FilterByPartNumberCommand(CollectionManager cManager){
        super("filter_by_part_number","вывести элементы, значение поля partNumber которых равно заданному");
        this.cManager = cManager;
    }


    @Override
    public void execute(BufferedReader reader) {
        filter();
    }

    @Override
    public void setArgument(String arg, HashMap<String, Command> commands) {
        this.partNumber = arg;
    }

    private void filter(){
        ArrayDeque<Product> copy = new ArrayDeque<Product>(cManager.getProductsCollection());

        for ( Product product: copy){
            if (product.getPartNumber().equals(this.partNumber)){
                System.out.println(copy.pop());
            }
            else{
                copy.pop();
            }
        }

    }

}
