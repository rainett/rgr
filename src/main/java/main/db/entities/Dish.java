package main.db.entities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Dish {
    private int id;
    private String name;
    private int price;
    private InputStream pic;
    private String pic64;

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

    public InputStream getPic() {
        return pic;
    }

    public void setPic(InputStream pic) {
        this.pic = pic;
    }

    public String getPic64() {
        try {
            return Base64.getEncoder().encodeToString(pic.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String toString() {
        return String.format("Dish[id = %d, name = %s, price = %s, pic = %s]", id, name, price, pic64);
    }
}
