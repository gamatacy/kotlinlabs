package productClasses;

import annotations.AutoGen;
import annotations.GreaterThan;
import annotations.LowerThan;
import annotations.NotNull;
import enums.OrganizationType;
import exceptions.CannotBeEmptyException;
import exceptions.CannotBeNullException;
import exceptions.InvalidValueException;

import java.util.Objects;

public class Organization {
    @NotNull
    @GreaterThan
    @AutoGen
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    private String fullName; //Поле не может быть null
    @NotNull
    private OrganizationType type; //Поле не может быть null
    @NotNull
    private Address officialAddress; //Поле не может быть null


    /**
     * @param id
     * @param name
     * @param fullName
     * @param type
     * @param zipcode
     * @throws InvalidValueException
     * @throws CannotBeNullException
     * @throws CannotBeEmptyException
     */
    public Organization(int id, String name, String fullName, OrganizationType type, String zipcode) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.type = type;
        this.officialAddress = new Address(zipcode);
    }

    /**
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * @return String fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return OrganizationType - Enum
     */
    public OrganizationType getType() {
        return type;
    }

    /**
     * @return Address
     */
    public Address getOfficialAddress() {
        return officialAddress;
    }


     /**
     * Organization equals
     * @param o
     * @return Boolean
     */

    @Override
    public boolean equals(Object o) {
        Organization equal = (Organization) o;
        if(equal.getName().equals(this.name) && equal.getFullName().equals(this.fullName) && equal.getType().equals(this.type) && equal.getOfficialAddress().getZipCode().equals(this.officialAddress.getZipCode())){
            return true;
        }
        else {
            return false;}
    }


    /**
     * @return int
     */



    @Override
    public String toString() {

        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", type=" + type +
                ", officialAddress=" + officialAddress.getZipCode() +
                '}';
    }
}

