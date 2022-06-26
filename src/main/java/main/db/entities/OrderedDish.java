package main.db.entities;

public class OrderedDish {
    private int id;
    private int orderId;
    private int dishId;
    private int dishAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getDishAmount() {
        return dishAmount;
    }

    public void setDishAmount(int dishAmount) {
        this.dishAmount = dishAmount;
    }

    @Override
    public String toString() {
        return String.format(
                "OrderedDish[orderedId = %d, dishId = %d, dishAmount = %s]",
                id, dishId, dishAmount
        );
    }
}
