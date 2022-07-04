package main.db.entities;

public class Order {
    private int id;
    private int userId;
    private int paymentId;
    private int addressId;
    private int price;
    private int stateId;
    private int workerId;

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

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    @Override
    public String toString() {
        return String.format(
                "Order[orderId = %d, clientId = %d, cardId = %s, addressId = %s, price = %s, stateId = %d, workerId = %d]",
                id, userId, paymentId, addressId, price, stateId, workerId
        );
    }
}
