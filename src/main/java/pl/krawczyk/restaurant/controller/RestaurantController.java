package pl.krawczyk.restaurant.controller;

import java.util.List;
import pl.krawczyk.restaurant.enums.FieldType;
import pl.krawczyk.restaurant.model.Customer;
import pl.krawczyk.restaurant.model.Deliverer;
import pl.krawczyk.restaurant.model.Field;
import pl.krawczyk.restaurant.model.Map;
import pl.krawczyk.restaurant.model.Order;
import pl.krawczyk.restaurant.model.Restaurant;
import pl.krawczyk.restaurant.model.SingleMeal;

public class RestaurantController {

    private static RestaurantController restaurantController = null;

    private final Restaurant restaurant = Restaurant.getInstance();
    private final MapController mapController = MapController.getInstance();

    private RestaurantController() {
    }

    public synchronized static RestaurantController getInstance() {
        if (restaurantController == null) {
            restaurantController = new RestaurantController();
        }
        return restaurantController;
    }

    public int getX() {
        return restaurant.getX();
    }

    public int getY() {
        return restaurant.getY();
    }
    
    public FieldType getFiledType(Field field) {
        return mapController.getMap().getField(field);
    }

    public void addDeliverer(Deliverer deliverer) {
        restaurant.addDeliverer(deliverer);
        Thread thread = new Thread(deliverer);
        thread.start();
    }

    public void addCustomer(Customer customer) {
        // jeśli współrzędne mieszczą się na mapie i są na polu ADDRESS, to dodaje klienta
        if (customer.getX() < Map.SIZE && customer.getY() < Map.SIZE) {
            FieldType fieldType = mapController.getMap().getField(customer.getX(), customer.getY());
            if (FieldType.ADDRESS.equals(fieldType)) {
                
                restaurant.addCustomer(customer);
                Thread thread = new Thread(customer);
                thread.start();
            }
        }
    }
    
    public void addOrder(Order order) {
        restaurant.addOrder(order);
    }

    public void addMeal(SingleMeal meal) {
        restaurant.addMeal(meal);
    }

    public List<SingleMeal> getMeals() {
        return restaurant.getMeals();
    }
    
    public int countCustomers() {
        return restaurant.getCustomers().size();
    }
    
    public int countOrders() {
        return restaurant.getOrders().size();
    }
    
    public int countDeliverers() {
        return restaurant.getDeliverers().size();
    }
    
    public int countMeals() {
        return restaurant.getMeals().size();
    }

    public Customer getCustomerByField(Field field) {
        Customer result = null;
        for (Customer customer : restaurant.getCustomers()) {
            if (customer.getX() == field.getX() && customer.getY() == field.getY()) {
                result = customer;
                break;
            }
        }
        return result;
    }
    
    public Deliverer getDelivererByField(Field field) {
        Deliverer result = null;
        for (Deliverer deliverer : restaurant.getDeliverers()) {
            if (deliverer.getX() == field.getX() && deliverer.getY() == field.getY()) {
                result = deliverer;
                break;
            }
        }
        return result;
    }

    public void removeCustomer(Customer customer) {
        restaurant.removeCustomer(customer);
    }
    
    public void removeDeliverer(Deliverer deliverer) {
        restaurant.removeDeliverer(deliverer);
    }
    
    public void removeOrder(Order order) {
        restaurant.removeOrder(order);
    }
}
