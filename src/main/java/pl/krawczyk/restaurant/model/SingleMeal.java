package pl.krawczyk.restaurant.model;

import java.util.ArrayList;
import java.util.List;
import pl.krawczyk.restaurant.enums.MealSize;
import pl.krawczyk.restaurant.enums.MealType;

// klasa pojedynczego posiłku
public class SingleMeal implements Meal {
    
    private String name;
    private List<String> components = new ArrayList<>();
    private double price;
    private MealType mealType;
    private MealSize mealSize;

    public SingleMeal(String name, double price, MealType mealType, MealSize mealSize) {
        this.name = name;
        this.price = price;
        this.mealType = mealType;
        this.mealSize = mealSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public MealSize getMealSize() {
        return mealSize;
    }

    public void setMealSize(MealSize mealSize) {
        this.mealSize = mealSize;
    }
}
