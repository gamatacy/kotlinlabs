package productClasses;
import annotations.*;
import enums.OrganizationType;
import enums.UnitOfMeasure;
import exceptions.CannotBeEmptyException;
import exceptions.CannotBeNegativeException;
import exceptions.CannotBeNullException;
import exceptions.InvalidValueException;


import java.util.Date;
import java.util.HashSet;

public class Product implements Comparable<Product> {
    @NotNull
    @GreaterThan
    @AutoGen
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    private Coordinates coordinates; //Поле не может быть null
    @NotNull
    @AutoGen
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @NotNull
    @GreaterThan
    private Float price; //Поле может быть null, Значение поля должно быть больше 0
    @NotNull
    private String partNumber; //Поле не может быть null
    private int manufactureCost;
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Organization manufacturer; //Поле может быть null

    public Product(
            Integer id,
            String name,
            Coordinates coordinates,
            Date creationDate,
            Float price,
            String partNumber,
            int manufactureCost ,
            UnitOfMeasure unitOfMeasure,
            Organization manufacturer
    ){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    public Object[] productToObject(){
        Object[] objects = new Object[14];
        objects[0] = this.id;
        objects[1] = this.name;
        objects[2] = this.coordinates.getX();
        objects[3] = this.coordinates.getY();
        objects[4] = this.creationDate;
        objects[5] = this.price;
        objects[6] = this.partNumber;
        objects[7] = this.manufactureCost;
        objects[8] = this.unitOfMeasure;
        objects[9] = this.manufacturer.getId();
        objects[10] = this.manufacturer.getName();
        objects[11] = this.manufacturer.getFullName();
        objects[12] = this.manufacturer.getType();
        objects[13] = this.manufacturer.getOfficialAddress().getZipCode();
        return objects;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", partNumber='" + partNumber + '\'' +
                ", manufactureCost=" + manufactureCost +
                ", unitOfMeasure=" + unitOfMeasure +
                ", manufacturer=" + manufacturer.toString() +
                '}';
    }

    public Integer getId() {
        return id;
    }

    /**
     * Compare products by id
     * @param product
     * @return int
     */
    @Override
    public int compareTo(Product product) {
        if(this.id < product.getId()    ){
            return -1;
        }
        else{
            return 1;
        }
    }

}