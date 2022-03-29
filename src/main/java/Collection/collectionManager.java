package Collection;

import Collection.Classes.Product;
import Enums.OrganizationType;
import Exceptions.cannotBeEmptyException;
import Exceptions.cannotBeNegativeException;
import Exceptions.cannotBeNullException;
import Exceptions.invalidValueException;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayDeque;

public class collectionManager implements fillCollection{
    public ArrayDeque<Product> productsCollection = new ArrayDeque<>();

    public ArrayDeque<Product> getProductsCollection() {
        return productsCollection;
    }

    public void addToCollectionFirst(Product p){
        productsCollection.addFirst(p);
    }

    public void addToCollectionLast(Product p){
        productsCollection.addLast(p);
    }

    public Product getCollectionLast(){
        return productsCollection.getLast();
    }

    public Product getCollectionFirst(){
        return productsCollection.getFirst();
    }

    public int getCollectionSize(){
        return productsCollection.size();
    }


    //добавить нормальный гет
    @Override
    public void fill(Iterable<CSVRecord> records){
        for (CSVRecord record : records) {
            Product product = new Product();

            try {
                product.setId(new Integer(record.get(0)));
                product.setName(record.get(1));
                product.setCoordinates(new Float(record.get(2)), Float.parseFloat(record.get(3)));
                product.setPrice(new Float(record.get(5)));
                product.setPartNumber(record.get(6));
                product.setManufactureCost(Integer.parseInt(record.get(7)));
                product.setUnitOfMeasure(record.get(8));
                product.setManufacturer(record.get(9), record.get(10), record.get(11), record.get(12));
                addToCollectionFirst(product);
            }
            catch (Throwable e){
                 System.err.println(e.getMessage());
            }
        }
    }

}
