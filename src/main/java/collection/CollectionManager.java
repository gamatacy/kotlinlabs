package collection;

import exceptions.InvalidValueException;
import productClasses.*;
import org.apache.commons.csv.CSVRecord;
import utils.StringToObject;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Manager class for interact with collection
 */

public class CollectionManager implements FillCollection {
    private java.util.Date creationDate;
    private ArrayDeque<Product> productsCollection = new ArrayDeque<>();

    public CollectionManager() {
        this.creationDate = new Date();
    }

    /**
     * @return creationDate of collection
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @return Collection of products
     */
    public ArrayDeque<Product> getProductsCollection() {
        return productsCollection;
    }

    /**
     * @param collection Product collection
     */
    public void updateCollection(ArrayDeque<Product> collection) {
        this.productsCollection = collection;
    }

    /**
     * @param p Product
     */
    public void addToCollectionFirst(Product p) {
        productsCollection.addFirst(p);
    }


    /**
     * @return Product
     */
    public Product getCollectionFirst() throws InvalidValueException{
        if (this.productsCollection.size() != 0) {
            return productsCollection.getFirst();
        }else{
            throw new InvalidValueException();
        }
    }

    /**
     * @return Collection size
     */
    public int getCollectionSize() {
        return productsCollection.size();
    }

    public Product removeFirst() throws InvalidValueException {
        if (this.productsCollection.size() != 0) {
            return this.productsCollection.pop();
        }else {
            throw new InvalidValueException();
        }
    }

    /**
     * Fill collection from other collection.
     *
     * @param records collection of CSVRecords
     */
    @Override
    public void fill(Iterable<CSVRecord> records) {
        int successCount = 0;
        int recordsCount = 0;
        Field[] fields = FieldsReader.getClassFields(Product.class);

        for (CSVRecord record : records) {
            Object[] values = new Object[fields.length];
            int counter = 0;
            successCount++;
            recordsCount++;
            while (counter < fields.length) {
                Field field = fields[counter];
                String input = record.get(counter);
                try {
                    Object objectValue = StringToObject.convert(input, field.getType());
                    FieldsValidator.validateField(input, field);
                    values[counter] = objectValue;
                } catch (Exception e) {
                    successCount--;
                    break;
                }
                counter++;
            }
            if (counter == fields.length) {
                addToCollectionFirst(ProductBuilder.getBuilder().buildCustomIdDateProduct(values));
            }
        }

        if(successCount == recordsCount){
            System.out.println("Collection from file loaded successfully");
        }else if(successCount == 0){
            System.out.println("Failed to load collection from file");
        }else{
            System.out.println(successCount + " element from file loaded to collection");
        }
    }
}


