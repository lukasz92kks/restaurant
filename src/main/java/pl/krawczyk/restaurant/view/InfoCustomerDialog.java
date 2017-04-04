package pl.krawczyk.restaurant.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import pl.krawczyk.restaurant.controller.MapController;
import pl.krawczyk.restaurant.controller.RestaurantController;
import pl.krawczyk.restaurant.enums.FieldType;
import pl.krawczyk.restaurant.model.Customer;

public class InfoCustomerDialog extends JDialog {
    
    private final MapController mapController = MapController.getInstance();
    private final RestaurantController restaurantController = RestaurantController.getInstance();
    
    public InfoCustomerDialog(Customer customer) {
        this.setSize(300, 300);
        this.setLayout(new GridLayout(5, 1));
        
        JLabel nameLabel = new JLabel("Imię: " + customer.getName());
        JLabel addressLabel = new JLabel("Adres: " + customer.getAddress());
        JLabel emailLabel = new JLabel("Email: " + customer.getEmail());
        JLabel phoneLabel = new JLabel("Telefon: " + customer.getPhone());
        JLabel priceLabel = new JLabel("Do zapłaty: " + (customer.getOrder() == null ? 0 : customer.getOrder().getPrice()));
        
        add(nameLabel);
        add(addressLabel);
        add(emailLabel);
        add(phoneLabel);
        add(priceLabel);
        
        // przycisk "Usuń klienta"
        JButton removeCustomerButton = new JButton("Usuń klienta");
        removeCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapController.getMap().setField(customer.getX(), customer.getY(), FieldType.ADDRESS);
                restaurantController.removeOrder(customer.getOrder());
                restaurantController.removeCustomer(customer);
            }
        });
        this.add(removeCustomerButton);
    }
}
