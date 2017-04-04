package pl.krawczyk.restaurant.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import pl.krawczyk.restaurant.controller.MapController;
import pl.krawczyk.restaurant.controller.RestaurantController;
import pl.krawczyk.restaurant.enums.MealSize;
import pl.krawczyk.restaurant.enums.MealType;
import pl.krawczyk.restaurant.model.Deliverer;
import pl.krawczyk.restaurant.model.OccasionalCustomer;
import pl.krawczyk.restaurant.model.SingleMeal;

// panel kontrolny
public class ControlPanel extends JPanel implements ActionListener {

    private final Timer timer = new Timer(1000, this);
    private final Random random = new Random();
    private final MapController mapController = MapController.getInstance();
    private final RestaurantController restaurantController = RestaurantController.getInstance();
    
    private final JLabel countMealsLabel;
    private final JLabel countCustomersLabel;
    private final JLabel countDeliverersLabel;

    public ControlPanel() {
        timer.start();  // uruchomienie timera
        
        // ustawienie wyglądu
        this.setLayout(new GridLayout(15, 1));
        
        // przycisk "Dodaj dostawcę"
        JButton addDelivererButton = new JButton("Dodaj dostawcę");
        addDelivererButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Deliverer deliverer = new Deliverer(restaurantController.getX(), restaurantController.getY());
                deliverer.setName("Dostawca");
                deliverer.setLastname(Integer.toString(random.nextInt(50)));
                deliverer.setPesel(Integer.toString(random.nextInt(1000000000) + 1000000000));
                restaurantController.addDeliverer(deliverer);
            }
        });
        this.add(addDelivererButton);

        // przycisk "Dodaj zamówienie"
        JButton addOrderButton = new JButton("Dodaj zamówienie");
        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField xField = new JTextField();
                JTextField yField = new JTextField();
                Object[] message = {
                    "x:", xField,
                    "y:", yField
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Podaj adres", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    int x = Integer.parseInt(xField.getText());
                    int y = Integer.parseInt(yField.getText());

                    OccasionalCustomer customer = new OccasionalCustomer("name", "666555444",
                            Integer.toString(x) + Integer.toString(y), "customer@gmail.com", x, y, 0);
                    restaurantController.addCustomer(customer);
                }

            }
        });
        this.add(addOrderButton);
        
        // przycisk "Dodaj posiłek"
        JButton addMealButton = new JButton("Dodaj posiłek");
        addMealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = "Food " + random.nextInt(99) + 1;
                int price = random.nextInt(20) + 5;
                MealType type = MealType.values()[random.nextInt(MealType.values().length)];
                MealSize size = MealSize.values()[random.nextInt(MealSize.values().length)];
                SingleMeal meal = new SingleMeal(name, price, type, size);
                restaurantController.addMeal(meal);
            }
        });
        this.add(addMealButton);
        
        // przycisk Lista posiłków
        JButton mealsListButton = new JButton("Lista posiłków");
        mealsListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MealsListDialog dialog = new MealsListDialog();
                dialog.setVisible(true);
            }
        });
        this.add(mealsListButton);
        
        // etykiety ze statystykami
        countMealsLabel = new JLabel("Liczba posiłków: " + restaurantController.countMeals());
        countCustomersLabel = new JLabel("Liczba klientów: " + restaurantController.countCustomers());
        countDeliverersLabel = new JLabel("Liczba dostawców: " + restaurantController.countDeliverers());
        
        this.add(countMealsLabel);
        this.add(countCustomersLabel);
        this.add(countDeliverersLabel);
    }
    
    protected void updateLabels() {
        // etykiety ze statystykami
        countMealsLabel.setText("Liczba posiłków: " + restaurantController.countMeals());
        countCustomersLabel.setText("Liczba klientów: " + restaurantController.countCustomers());
        countDeliverersLabel.setText("Liczba dostawców: " + restaurantController.countDeliverers());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            updateLabels();
        }
    }
}
