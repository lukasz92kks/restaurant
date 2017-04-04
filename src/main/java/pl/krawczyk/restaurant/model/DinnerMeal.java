package pl.krawczyk.restaurant.model;

import java.util.ArrayList;
import java.util.List;

// klasa zestawu obiadowego
public class DinnerMeal implements Meal {
    
    private List<SingleMeal> meals = new ArrayList<>(); // lista posiłków
    private double discount;                            // rabat

    public List<SingleMeal> getMeals() {
        return meals;
    }

    public void setMeals(List<SingleMeal> meals) {
        this.meals = meals;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        double price = 0;
        for (SingleMeal meal : meals) {
            price += meal.getPrice();
        }
        price *= discount;
        return price;
    }
}
