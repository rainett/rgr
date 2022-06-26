package main.db.entities;

public class Payment {
    private int id;
    private int userId;
    private String number;
    private String till;
    private String cvv;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTill() {
        return till;
    }

    public void setTill(String till) {
        this.till = till;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return String.format(
                "Address[cardId = %d, clientId = %d, number = %s, till = %s, cvv = %s]",
                id, userId, number, till, cvv
        );
    }
}
