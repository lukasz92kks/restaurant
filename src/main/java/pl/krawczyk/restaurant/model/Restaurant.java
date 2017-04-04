package pl.krawczyk.restaurant.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.krawczyk.restaurant.enums.MealSize;
import pl.krawczyk.restaurant.enums.MealType;

// klasa restauracji
public class Restaurant extends Drawable {

    private static final int POSITION_X = 16;   // pozycja X na mapie (restauracji)
    private static final int POSITION_Y = 16;   // pozycja Y na mapie (restauracji)

    private List<SingleMeal> meals = new ArrayList<>();         // posiłki
    private List<Order> orders = new ArrayList<>();             // zamównia
    private List<Customer> customers = new ArrayList<>();       // klienci
    private List<Deliverer> deliverers = new ArrayList<>();     // dostawcy
    private List<Vehicle> vehicles = new ArrayList<>();         // pojazdy

    private static Restaurant restaurant = null;

    public synchronized static Restaurant getInstance() {
        if (restaurant == null) {
            restaurant = new Restaurant(POSITION_X, POSITION_Y);
        }
        return restaurant;
    }

    private Restaurant(int x, int y) {
        super(x, y);
        initMeals();

    }

    // tworzy menu
    private void initMeals() {
        meals.add(new SingleMeal("Margherita", 10, MealType.PIZZA, MealSize.SMALL));
        meals.add(new SingleMeal("Margherita", 15, MealType.PIZZA, MealSize.BIG));
        meals.add(new SingleMeal("Lasagna", 8, MealType.PASTA, MealSize.MEDIUM));
        meals.add(new SingleMeal("Spaghetti", 8, MealType.PASTA, MealSize.MEDIUM));
        meals.add(new SingleMeal("Ice cream", 11, MealType.DESSERT, MealSize.SMALL));
        meals.add(new SingleMeal("Cake", 9, MealType.DESSERT, MealSize.SMALL));
    }

    public List<SingleMeal> getMeals() {
        return meals;
    }

    public void setMeals(List<SingleMeal> meals) {
        this.meals = meals;
    }

    public void addMeal(SingleMeal meal) {
        meals.add(meal);
    }

    public void removeMeal(SingleMeal meal) {
        meals.remove(meal);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        try {
            semaphore.acquire();
            orders.add(order);

        } catch (InterruptedException ex) {
            Logger.getLogger(Restaurant.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            semaphore.release();
        }
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    // pobiera pierwsze zamównie z listy
    public synchronized Order popFirstOrder() {
        Order order = null;
        try {
            semaphore.acquire();

            if (restaurant.getOrders().size() > 0) {
                order = orders.get(0);
                orders.remove(order);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Restaurant.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            semaphore.release();
        }
        return order;
    }

    Semaphore semaphore = new Semaphore(1);

    public void printOrders() {
        try {
            semaphore.acquire();
            System.out.println("Orders:");
            for (Order order : orders) {
                SingleMeal meal = (SingleMeal) order.getMeals().get(0);
                System.out.println(meal.getName());
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Restaurant.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            semaphore.release();
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public List<Deliverer> getDeliverers() {
        return deliverers;
    }

    public void setDeliverers(List<Deliverer> deliverers) {
        this.deliverers = deliverers;
    }

    public void addDeliverer(Deliverer deliverer) {
        deliverers.add(deliverer);
    }

    public void removeDeliverer(Deliverer deliverer) {
        deliverers.remove(deliverer);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }
}
