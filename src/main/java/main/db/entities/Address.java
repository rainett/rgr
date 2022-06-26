package main.db.entities;

public class Address {
    private int id;
    private int userId;
    private String city;
    private String street;
    private String houseNumber;
    private String apartmentNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public String toString() {
        return String.format(
                "Address[addressId = %d, clientId = %d, city = %s, street = %s, houseNumber = %s, apartmentNumber = %s]",
                id, userId, city, street, houseNumber, apartmentNumber
        );
    }
}
