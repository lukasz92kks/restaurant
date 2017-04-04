package pl.krawczyk.restaurant.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.krawczyk.restaurant.controller.MapController;
import pl.krawczyk.restaurant.enums.FieldType;

// klasa abstrakcyjna klienta
public abstract class Customer extends DrawableThread implements Runnable {

    private String name;            // nazwa
    private String phone;           // telefon
    private String address;         // adres
    private Order order;            // zamówienie
    private Date orderTime;         // godzina zamówienia
    private String email;           // adres email
    private int randomBound = 6;    // parametr losowości = 1/6 szansy na wygenerowanie zlecenia co 1 sekundę

    private final Random random = new Random();
    private final Restaurant restaurant = Restaurant.getInstance();
    private final MapController mapController = MapController.getInstance();

    public Customer(String name, String phone, String address, String email, int x, int y) {
        super(x, y);
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Customer(String name, String phone, String address, String email, int x, int y, int randomBound) {
        this(name, phone, address, email, x, y);
        this.randomBound = randomBound;
    }

    // główna funkcja (pętla) wątku klienta
    @Override
    public void run() {
        while (true) {
            try {
                // warunek zapewniający losowość składanych zamówień
                if (randomBound == 0 || random.nextInt(randomBound) == 0) {
                    if (order == null) {
                        order = new Order();
                        order.setCustomer(this);
                        List<Meal> meals = new ArrayList<>();
                        Meal meal = restaurant.getMeals().get(random.nextInt(5));
                        meals.add(meal);

                        order.setMeals(meals);
                        restaurant.addOrder(order);
                        mapController.getMap().setField(getX(), getY(), FieldType.ORDER);
                        System.out.println("Orders: " + restaurant.getOrders().size());
                    }
                } else if (order == null) {
                    mapController.getMap().setField(getX(), getY(), FieldType.CUSTOMER);
                }

                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CompanyCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
