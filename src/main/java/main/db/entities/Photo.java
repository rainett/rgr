package main.db.entities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Photo {
    private int id;
    private InputStream pic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return String.format("Photo[id = %d]", id);
    }
}
