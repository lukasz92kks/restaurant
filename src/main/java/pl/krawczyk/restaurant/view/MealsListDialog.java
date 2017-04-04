package pl.krawczyk.restaurant.view;

import javax.swing.JDialog;
import javax.swing.JTable;
import pl.krawczyk.restaurant.controller.RestaurantController;
import pl.krawczyk.restaurant.model.SingleMeal;

// Okno dialogowe z listą posiłków
public class MealsListDialog extends JDialog {
    
    private final RestaurantController restaurantController = RestaurantController.getInstance();
    
    public MealsListDialog() {
        setSize(300, 300);
        
        Object[][] rowData = new Object[restaurantController.getMeals().size()][4];
        int i = 0;
        for (SingleMeal meal : restaurantController.getMeals()) {
            rowData[i][0] = meal.getName();
            rowData[i][1] = meal.getMealType();
            rowData[i][2] = meal.getMealSize();
            rowData[i][3] = meal.getPrice();
            ++i;
        }
        
        String[] columnNames = new String[] {
            "Nazwa", "Kategoria", "Rozmiar", "Cena"
        };
        
        JTable tableBox = new JTable(rowData, columnNames);
        add(tableBox);
    }
}
