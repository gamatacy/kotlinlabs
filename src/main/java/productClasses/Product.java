package productClasses;
import enums.OrganizationType;
import enums.UnitOfMeasure;
import exceptions.cannotBeEmptyException;
import exceptions.cannotBeNegativeException;
import exceptions.cannotBeNullException;
import exceptions.invalidValueException;


import java.util.Date;
import java.util.HashSet;

public class Product implements Comparable<Product> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float price; //Поле может быть null, Значение поля должно быть больше 0
    private String partNumber; //Поле не может быть null
    private int manufactureCost;
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Organization manufacturer; //Поле может быть null

    private static int usedId = 0;
    /**
     * Contains used IDs
     */
    private static HashSet<Integer> UsedIDs = new HashSet<Integer>();

    /**
     *
     * @param integer product ID
     * @throws invalidValueException
     * @throws cannotBeNullException
     */
    public Product(Integer integer) throws invalidValueException,cannotBeNullException{
        if (integer < 0){
            throw new invalidValueException("ID cannot be zero");
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

    /**
     * Constructor without param, generate ID automatically
     *
     */
    public Product(){
        while(UsedIDs.contains(usedId) == true){
            this.usedId += 1;
        }
        UsedIDs.add(usedId);
        this.id = usedId;
        this.creationDate = new Date();
    }

    /**
     *
     * @param integer
     * @throws invalidValueException
     * @throws cannotBeNullException
     */
    public void setId(Integer integer) throws invalidValueException, cannotBeNullException {
        if (integer < 0){
            throw new invalidValueException("ID cannot be zero");
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

    /**
     *
     * @param name
     * @throws cannotBeNullException
     * @throws cannotBeEmptyException
     */
    public void setName(String name) throws cannotBeNullException, cannotBeEmptyException{
        if (name.length() == 0){
            throw new cannotBeNullException();
        }
        else if (name.replaceAll(" ", "").length() == 0){
            throw new cannotBeEmptyException();
        }
        this.name = name;
    }

    /**
     *
     * @param x
     * @param y
     * @throws invalidValueException
     * @throws cannotBeNullException
     */
    public void setCoordinates(Float x, float y) throws invalidValueException, cannotBeNullException {
        this.coordinates = new Coordinates(x,y);
    }

    /**
     *
     * @param creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = new Date();
    }

    /**
     *
     * @param price
     * @throws cannotBeNegativeException
     */
    public void setPrice(Float price) throws cannotBeNegativeException{
        if (price < 0){
            throw new cannotBeNegativeException();
        }
        this.price = price;
    }

    /**
     * @param partNumber
     * @throws cannotBeNullException
     */
    public void setPartNumber(String partNumber) throws cannotBeNullException{
        if (partNumber.length() == 0) {
            throw new cannotBeNullException();
        }
        else{
            this.partNumber = partNumber;
        }
    }

    /**
     *
     * @param manufactureCost
     */
    public void setManufactureCost(int manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    /**
     *
     * @param name
     */
    public void setUnitOfMeasure(String name){
        this.unitOfMeasure = UnitOfMeasure.equals(name);
    }

    /**
     *
     * @param name
     * @param fullName
     * @param type
     * @param zipcode
     * @throws invalidValueException
     * @throws cannotBeNullException
     * @throws cannotBeEmptyException
     */
    public void setManufacturer(String name, String fullName, String type, String zipcode) throws invalidValueException, cannotBeNullException, cannotBeEmptyException {
        try {
            this.manufacturer = new Organization(this.id, name, fullName, OrganizationType.equals(type), zipcode);
        }catch (Throwable e){
            e.getMessage();
            this.manufacturer = new Organization();
        }
    }

    /**
     *
     * @param organization
     */
    public void setOrganization(Organization organization){
        this.manufacturer = organization;
    }

    /**
     *
     * @return Integer ID
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     *
     * @return Date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @return Float price
     */
    public Float getPrice() {
        return price;
    }

    /**
     *
     * @return String partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     *
     * @return int manufactureCost
     */
    public int getManufactureCost() {
        return manufactureCost;
    }

    /**
     *
     * @return UnitOfMeasure - Enum
     */
    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     *
     * @return Organization
     */
    public Organization getManufacturer() {
        return manufacturer;
    }

    /**
     * Delete id from usedID's
     * @param id
     */
    public void removeId(Integer id){
        UsedIDs.remove(id);
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

    /**
     * Compare products by id
     * @param product
     * @return int
     */
    @Override
    public int compareTo(Product product) {
        if(this.id < product.id){
            return -1;
        }
        else{
            return 1;
        }
    }

}