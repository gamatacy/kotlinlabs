package productClasses;

import annotations.AutoGen;
import annotations.GreaterThan;
import annotations.NotNull;
import enums.OrganizationType;
import exceptions.CannotBeEmptyException;
import exceptions.CannotBeNullException;
import exceptions.InvalidValueException;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="organizations")
public class Organization implements Serializable {
    @Id
    @NotNull
    @GreaterThan
    @AutoGen
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    private String fullName; //Поле не может быть null
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="organizationtype")
    private OrganizationType type; //Поле не может быть null
    @NotNull
    @Column(name="address")
    private String officialAddress; //Поле не может быть null


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
        this.officialAddress = zipcode;//new Address(zipcode);
    }

    public Organization(){};

    /**
     * @return int id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public String getOfficialAddress() {
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
        if(equal.getName().equals(this.name) && equal.getFullName().equals(this.fullName) && equal.getType().equals(this.type) && equal.getOfficialAddress().equals(this.officialAddress)){
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
                ", officialAddress=" + officialAddress +
                '}';
    }
}

