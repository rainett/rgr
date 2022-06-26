package main.db.entities;

public class Order {
    private int id;
    private int userId;
    private int paymentId;
    private int addressId;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

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

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return String.format(
                "Order[orderId = %d, clientId = %d, cardId = %s, addressId = %s, price = %s]",
                id, userId, paymentId, addressId, price
        );
    }
}
