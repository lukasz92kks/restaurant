package pl.krawczyk.restaurant.model;

// klasa stałego klienta
public class RegularCustomer extends Customer {
    
    private int points;     // punkty lojalnościowe
    private int discount;   // zniżka

    public RegularCustomer(String name, String phone, String address, String email, int x, int y) {
        super(name, phone, address, email, x, y);
    }
    
    public RegularCustomer(String name, String phone, String address, String email, int x, int y, int randomBound) {
        super(name, phone, address, email, x, y, randomBound);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
