package collection;

import productClasses.Product;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayDeque;
import java.util.Date;

/**
 *  Manager class for interact with collection
 * */

public class CollectionManager implements FillCollection {
    private java.util.Date creationDate;
    private ArrayDeque<Product> productsCollection = new ArrayDeque<>();

    public CollectionManager(){
        this.creationDate = new Date();
    }

    /**
     *
     * @return creationDate of collection
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @return Collection of products
     */
    public ArrayDeque<Product> getProductsCollection() {
        return productsCollection;
    }

    /**
     *
     * @param collection Product collection
     */
    public void updateCollection(ArrayDeque<Product> collection){
        this.productsCollection = collection;
    }

    /**
     *
     * @param p Product
     */
    public void addToCollectionFirst(Product p){
        productsCollection.addFirst(p);
    }

    /**
     *
     * @param p Product
     */
    public void addToCollectionLast(Product p){
        productsCollection.addLast(p);
    }

    /**
     *
     * @return Product
     */
    public Product getCollectionLast(){
        return productsCollection.getLast();
    }

    /**
     *
     * @return Product
     */
    public Product getCollectionFirst(){
        return productsCollection.getFirst();
    }

    /**
     *
     * @return Collection size
     */
    public int getCollectionSize(){
        return productsCollection.size();
    }


    /**
     * Fill collection from other collection.
     *
     * @param records collection of CSVRecords
     */
    @Override
    public void fill(Iterable<CSVRecord> records){
        for (CSVRecord record : records) {
            try {
                Product product = new Product(Integer.parseInt(record.get(0)));
                product.setName(record.get(1));
                product.setCoordinates(Float.parseFloat(record.get(2)), Float.parseFloat(record.get(3)));
                product.setPrice(Float.parseFloat(record.get(5)));
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
