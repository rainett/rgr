package main.db.entities;

public class Order {
    private int orderId;
    private int clientId;
    private int orderedId;
    private int cardId;
    private int addressId;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getOrderedId() {
        return orderedId;
    }

    public void setOrderedId(int orderedId) {
        this.orderedId = orderedId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
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
                "Order[orderId = %d, clientId = %d, orderedId = %s, cardId = %s, addressId = %s, price = %s]",
                orderId, clientId, orderedId, cardId, addressId, price
        );
    }
}
