package Collection.Classes;

public class Address {
    private String zipCode; //Поле может быть null

    public Address(String zipcode) {
        this.zipCode = zipcode;
    }

    public String getZipCode() {
        return zipCode;
    }
}
