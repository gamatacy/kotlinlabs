package Collection.Classes;
import Enums.OrganizationType;
import Exceptions.cannotBeEmptyException;
import Exceptions.cannotBeNullException;
import Exceptions.invalidValueException;

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

    public Organization(int id,String name, String fullName, OrganizationType type,String zipcode) throws invalidValueException, cannotBeNullException, cannotBeEmptyException {
        if (name.length() == 0){
            throw new cannotBeNullException();
        }
        else if (name.replaceAll(" ", "").length() == 0){
            throw new cannotBeEmptyException();
        }
        if (fullName.length() == 0){
            throw new cannotBeNullException();
        }
        else if (fullName.length() > 844){
            throw new invalidValueException("Maximum value 844");
        }
        if (type == null){
            throw new cannotBeNullException();
        }
        if (zipcode.length() == 0){
            throw new cannotBeNullException();
        }

        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.type = type;
        this.officialAddress = new Address(zipcode);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public void setName(String name) throws cannotBeNullException{
        if (name.length() == 0){
            throw new cannotBeNullException();
        }
        this.name = name;
    }

    public void setFullName(String fullName) throws cannotBeNullException, invalidValueException {
        if (fullName.length() == 0){
            throw new cannotBeNullException();
        }
        else if(fullName.length() > 844){
            throw new invalidValueException("Maximum length is 844");
        }

        this.fullName = fullName;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(OrganizationType type) throws cannotBeNullException {
        if (type == null){
            throw new cannotBeNullException();
        }
        this.type = type;

    }

    public void setOfficialAddress(Address officialAddress) {

        this.officialAddress = officialAddress;

    }

    public void setOrganizationExist(boolean organizationExist) {
        OrganizationExist = organizationExist;
    }

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
