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
import pl.krawczyk.restaurant.model.Deliverer;

public class InfoDelivererDialog extends JDialog {
    
    private final MapController mapController = MapController.getInstance();
    private final RestaurantController restaurantController = RestaurantController.getInstance();
    
    public InfoDelivererDialog(Deliverer deliverer) {
        this.setSize(300, 300);
        this.setLayout(new GridLayout(6, 1));
        
        JLabel nameLabel = new JLabel("Imię: " + deliverer.getName());
        JLabel addressLabel = new JLabel("Nazwisko: " + deliverer.getLastname());
        JLabel emailLabel = new JLabel("PESEL: " + deliverer.getPesel());
        
        add(nameLabel);
        add(addressLabel);
        add(emailLabel);
        
        // przycisk "Awaria"
        JButton damageButton = new JButton("Awaria");
        damageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (deliverer.getOrder() != null) {
                    restaurantController.addOrder(deliverer.getOrder());
                }
                deliverer.setOrder(null);
                deliverer.getVehicle().setDamage(true);
            }
        });
        add(damageButton);
        
        // przycisk "Usuń dostawcę"
        JButton removeDelivererButton = new JButton("Usuń dostawcę");
        removeDelivererButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!FieldType.RESTAURANT.equals(mapController.getMap().getField(deliverer.getX(), deliverer.getY()))) {
                    mapController.getMap().setField(deliverer.getX(), deliverer.getY(), FieldType.ROAD);
                }
                if (deliverer.getOrder() != null) {
                    restaurantController.addOrder(deliverer.getOrder());
                }
                restaurantController.removeDeliverer(deliverer);
                deliverer.stop();
            }
        });
        add(removeDelivererButton);
    }
}
