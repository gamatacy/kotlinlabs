package Collection.Classes;
import Enums.OrganizationType;
import Enums.UnitOfMeasure;
import Exceptions.cannotBeEmptyException;
import Exceptions.cannotBeNegativeException;
import Exceptions.cannotBeNullException;
import Exceptions.invalidValueException;


import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;


public class Product{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float price; //Поле может быть null, Значение поля должно быть больше 0
    private String partNumber; //Поле не может быть null
    private int manufactureCost;
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Organization manufacturer; //Поле может быть null

    //Ерунда для айди
    static int usedId = 0;
    private static HashSet<Integer> UsedIDs = new HashSet<Integer>();

    public Product(){
        this.id = usedId + 1;
        UsedIDs.add(usedId);
        usedId += 1;
        this.creationDate = new Date();
    }

    public void setId(Integer integer) throws invalidValueException, cannotBeNullException {
        if (integer < 0){
            throw new invalidValueException("Cannot be zero");
        }
        else if (integer == null){
            throw new cannotBeNullException();
        }
        else if ( UsedIDs.contains(integer) ){
            throw new invalidValueException("ID already used");
        }
        this.id = integer;
        UsedIDs.add(integer);
    }

    public void setName(String name) throws cannotBeNullException, cannotBeEmptyException{
        if (name.length() == 0){
            throw new cannotBeNullException();
        }
        else if (name.replaceAll(" ", "").length() == 0){
            throw new cannotBeEmptyException();
        }
        this.name = name;
    }

    public void setCoordinates(Float x, float y) throws invalidValueException, cannotBeNullException {
        this.coordinates = new Coordinates(x,y);
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = new Date();
    }

    public void setPrice(Float price) throws cannotBeNegativeException{
        if (price < 0){
            throw new cannotBeNegativeException();
        }
        this.price = price;
    }

    public void setPartNumber(String partNumber) throws cannotBeNullException{
        if (partNumber.length() == 0) {
            throw new cannotBeNullException();
        }
        else{
            this.partNumber = partNumber;
        }
    }

    public void setManufactureCost(int manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    public void setUnitOfMeasure(String name){
        this.unitOfMeasure = UnitOfMeasure.equals(name);
    }

    public void setManufacturer(String name, String fullName, String type, String zipcode) throws invalidValueException, cannotBeNullException, cannotBeEmptyException {
        try {
            this.manufacturer = new Organization(this.id, name, fullName, OrganizationType.equals(type), zipcode);
        }catch (Throwable e){
            e.getMessage();
            this.manufacturer = new Organization();
        }
    }

    public void setOrganization(Organization organization){
        this.manufacturer = organization;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Float getPrice() {
        return price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public int getManufactureCost() {
        return manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
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
}