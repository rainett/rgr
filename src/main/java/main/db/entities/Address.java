package main.db.entities;

public class Address {
    private int addressId;
    private int clientId;
    private String city;
    private String street;
    private String houseNumber;
    private String apartmentNumber;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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
                addressId, clientId, city, street, houseNumber, apartmentNumber
        );
    }
}
