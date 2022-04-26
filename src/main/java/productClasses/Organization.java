package productClasses;
import enums.OrganizationType;
import exceptions.CannotBeEmptyException;
import exceptions.CannotBeNullException;
import exceptions.InvalidValueException;

import java.util.Objects;

public class Organization {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String fullName; //Длина строки не должна быть больше 844, Поле не может быть null
    private OrganizationType type; //Поле не может быть null
    private Address officialAddress; //Поле не может быть null
    private boolean OrganizationExist = true;

    public Organization(){
        OrganizationExist = false;
    }

    /**
     *
     * @param id
     * @param name
     * @param fullName
     * @param type
     * @param zipcode
     * @throws InvalidValueException
     * @throws CannotBeNullException
     * @throws CannotBeEmptyException
     */
    public Organization(int id,String name, String fullName, OrganizationType type,String zipcode) throws InvalidValueException, CannotBeNullException, CannotBeEmptyException {
        if (name.length() == 0){
            throw new CannotBeNullException();
        }
        else if (name.replaceAll(" ", "").length() == 0){
            throw new CannotBeEmptyException();
        }
        if (fullName.length() == 0){
            throw new CannotBeNullException();
        }
        else if (fullName.length() > 844){
            throw new InvalidValueException("Maximum value 844");
        }
        if (type == null){
            throw new CannotBeNullException();
        }
        if (zipcode.length() == 0){
            throw new CannotBeNullException();
        }

        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.type = type;
        this.officialAddress = new Address(zipcode);
    }

    /**
     *
     * @return int id
     */
    public int getId() {
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
     * @return String fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @return OrganizationType - Enum
     */
    public OrganizationType getType() {
        return type;
    }

    /**
     *
     * @return Address
     */
    public Address getOfficialAddress() {
        return officialAddress;
    }

    /**
     *
     * @param name
     * @throws CannotBeNullException
     */
    public void setName(String name) throws CannotBeNullException {
        if (name.length() == 0){
            throw new CannotBeNullException();
        }
        this.name = name;
    }

    /**
     *
     * @param fullName
     * @throws CannotBeNullException
     * @throws InvalidValueException
     */
    public void setFullName(String fullName) throws CannotBeNullException, InvalidValueException {
        if (fullName.length() == 0){
            throw new CannotBeNullException();
        }
        else if(fullName.length() > 844){
            throw new InvalidValueException("Maximum length is 844");
        }

        this.fullName = fullName;

    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param type
     * @throws CannotBeNullException
     */
    public void setType(OrganizationType type) throws CannotBeNullException {
        if (type == null){
            throw new CannotBeNullException();
        }
        this.type = type;

    }

    /**
     *
     * @param officialAddress
     */
    public void setOfficialAddress(Address officialAddress) {

        this.officialAddress = officialAddress;

    }

    /**
     *
     * @param organizationExist
     */
    public void setOrganizationExist(boolean organizationExist) {
        OrganizationExist = organizationExist;
    }

    /**
     * Organization equals
     * @param o
     * @return Boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        if(OrganizationExist == that.OrganizationExist){return true;}
        return id == that.id  && Objects.equals(name, that.name) && Objects.equals(fullName, that.fullName) && type == that.type && Objects.equals(officialAddress, that.officialAddress);
    }

    /**
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullName, type, officialAddress, OrganizationExist);
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        if(OrganizationExist == false){
            return "Organization=null";
        }
        else{
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", type=" + type +
                ", officialAddress=" + officialAddress.getZipCode() +
                '}';
        }
    }
}
