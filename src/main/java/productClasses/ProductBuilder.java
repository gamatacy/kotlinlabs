package productClasses;

import enums.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class ProductBuilder {
    private static ProductBuilder builder;
    private Integer usedIds;
    private Set<Integer> usedIdsList;

    private ProductBuilder() {
        this.usedIds = 1;
        this.usedIdsList = new HashSet<Integer>();
    }

    public static ProductBuilder newBuilder() {
        builder = new ProductBuilder();
        return builder;
    }

    public static ProductBuilder getBuilder() {
        return builder;
    }

    public Product build(Integer id,
                         String name,
                         Coordinates coordinates,
                         Date creationDate,
                         Float price,
                         String partNumber,
                         int manufactureCost,
                         UnitOfMeasure unitOfMeasure,
                         Organization manufacturer) {

        Product product = new Product(id, name, coordinates, creationDate, price, partNumber, manufactureCost, unitOfMeasure, manufacturer);
        return product;
    }

    public Product build(Object[] productFields) {

        Integer id = (Integer) productFields[0];
        String name = (String) productFields[1];
        Coordinates coordinates = new Coordinates((Float) productFields[2], (float) productFields[3]);
        Date creationDate = (Date) productFields[4];
        Float price = (Float) productFields[5];
        String partNumber = (String) productFields[6];
        int manufactureCost = (Integer) productFields[7];
        UnitOfMeasure unitOfMeasure = (UnitOfMeasure) productFields[8];
        Organization manufacturer = new Organization((Integer) productFields[0], (String) productFields[10], (String) productFields[11], (OrganizationType) productFields[12], (String) productFields[13]);

        return build(id, name, coordinates, creationDate, price, partNumber, manufactureCost, unitOfMeasure, manufacturer);
    }

    public Product buildProduct(Object[] productFields) {
        Integer id = idGenerator();

        List<Object> fields = new ArrayList<Object>(Arrays.asList(productFields));
        fields.remove(0);
        fields.add(0, id);
        fields.remove(4);
        fields.add(4, new Date());

        return build(fields.toArray());
    }

    public Product buildCustomIdDateProduct(Object[] productFields) {
        Integer customId = (Integer) productFields[0];

        if (usedIdsList.contains(customId)) {
            customId = idGenerator();
        }
        else {
            usedIdsList.add(customId);
        }

        List<Object> fields = new ArrayList<Object>(Arrays.asList(productFields));
        fields.remove(0);
        fields.add(0, customId);
        fields.remove(4);
        fields.add(4, createDate((String) productFields[4]));
        return build(fields.toArray());
    }

    public Product buildManufacturer(Object[] productFields){
        Organization manufacturer = new Organization(0, (String) productFields[1], (String) productFields[2], (OrganizationType) productFields[3], (String) productFields[4]);
        return build(null, null, null, null, null, null, 0, null, manufacturer);
    }

    private Date createDate(String str){
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy");
        try {
            Date date = formatter.parse(str);
            return date;
        }catch (Exception e){
            return new Date();
        }
    }

    private Integer idGenerator() {
        while (usedIdsList.contains(usedIds)) {
            usedIds += 1;
        }
        usedIdsList.add(usedIds);
        return usedIds;
    }

    public void clearId(){this.usedIdsList = new HashSet<>(); usedIds = 0;}

    public void removeId(Integer id){
        this.usedIdsList.remove(id);
    }

}
