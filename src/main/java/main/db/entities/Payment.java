package main.db.entities;

public class Payment {
    private int cardId;
    private int clientId;
    private String number;
    private String till;
    private String cvv;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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
                cardId, clientId, number, till, cvv
        );
    }
}
