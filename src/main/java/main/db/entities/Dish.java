package main.db.entities;

public class Dish {
    private int id;
    private String name;
    private int price;
    private int photoID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    @Override
    public String toString() {
        return String.format("Dish[id = %d, name = %s, price = %s, photoId = %d]", id, name, price, photoID);
    }
}
