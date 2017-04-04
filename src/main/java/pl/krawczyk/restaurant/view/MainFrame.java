package pl.krawczyk.restaurant.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pl.krawczyk.restaurant.controller.RestaurantController;

// główne okno programu
public class MainFrame extends JFrame {
    
    RestaurantController restaurantController = RestaurantController.getInstance();
    
    public MainFrame() {
        setSize(680, 600);
        JPanel mapPanel = new MapPanel();
        add(mapPanel, BorderLayout.CENTER);
        JPanel controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.EAST);
    }
}
