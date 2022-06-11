package main.db.entities;

public class OrderedDish {
    private int orderedId;
    private int dishId;
    private int dishAmount;

    public int getOrderedId() {
        return orderedId;
    }

    public void setOrderedId(int orderedId) {
        this.orderedId = orderedId;
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
                orderedId, dishId, dishAmount
        );
    }
}
