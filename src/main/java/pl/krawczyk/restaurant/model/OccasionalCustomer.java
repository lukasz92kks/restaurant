package pl.krawczyk.restaurant.model;

// klasa klienta okazjonalnego
public class OccasionalCustomer extends Customer {

    public OccasionalCustomer(String name, String phone, String address, String email, int x, int y) {
        super(name, phone, address, email, x, y);
    }
    
    public OccasionalCustomer(String name, String phone, String address, String email, int x, int y, int randomBound) {
        super(name, phone, address, email, x, y, randomBound);
    }
}
