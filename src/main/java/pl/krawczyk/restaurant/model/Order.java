package pl.krawczyk.restaurant.model;

import java.util.ArrayList;
import java.util.List;

// klasa zamównia
public class Order implements Meal {
    
    private static final int FREE_DELIVERY_LIMIT = 100;     // minimalna kwota dla darmowej dostawy
    private static final int DELIVERY_PRICE = 5;            // cena dostawy
    
    private List<Meal> meals = new ArrayList<>();           // lista posiłków i zestawów obiadowych
    private Customer customer;                              // klient

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public double getPrice() {
        double price = 0;
        for (Meal meal : meals) {
            price += meal.getPrice();
        }
        if (price < FREE_DELIVERY_LIMIT) {              // jeśli suma zbyt niska
            price += DELIVERY_PRICE;                    // dodajemy opłatę za dowóz
        }
        return price;
    }
}