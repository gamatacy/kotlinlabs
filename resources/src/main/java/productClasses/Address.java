package productClasses;

import java.io.Serializable;

public class Address implements Serializable {
    private String zipCode; //Поле может быть null

    public Address(String zipcode) {
        this.zipCode = zipcode;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                '}';
    }
}
