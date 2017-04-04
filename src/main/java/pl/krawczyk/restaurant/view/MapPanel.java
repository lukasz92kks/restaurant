package pl.krawczyk.restaurant.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import javax.swing.Timer;
import pl.krawczyk.restaurant.controller.MapController;
import pl.krawczyk.restaurant.controller.RestaurantController;
import pl.krawczyk.restaurant.enums.FieldType;
import pl.krawczyk.restaurant.model.Customer;
import pl.krawczyk.restaurant.model.Deliverer;
import pl.krawczyk.restaurant.model.Field;
import pl.krawczyk.restaurant.model.OccasionalCustomer;

// panel z mapą
public class MapPanel extends JPanel implements ActionListener {

    private final int width = 15; // szerokość pojedynczego pola
    private final int space = 1;  // odstęp pomiędzy polami na mapie

    private final Timer timer = new Timer(500, this);
    private final MapController mapController = MapController.getInstance();
    private final RestaurantController restaurantController = RestaurantController.getInstance();

    public MapPanel() {
        timer.start();  // uruchomienie timera

        // obsługa myszki
        addMouseListener(new MouseAdapter() {
            private Color background;

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / (width + space);
                int y = e.getY() / (width + space);

                switch (restaurantController.getFiledType(new Field(x, y))) {
                    case CUSTOMER:
                    case ORDER:
                        infoCustomer(x, y);
                        break;
                    case DELIVERER:
                        infoDeliverer(x, y);
                        break;
                    case ADDRESS:
                        addCustomer(x, y);
                        break;
                }

            }
        });
    }

    private void infoCustomer(int x, int y) {
        Customer customer = restaurantController.getCustomerByField(new Field(x, y));
        if (customer != null) {
            InfoCustomerDialog dialog = new InfoCustomerDialog(customer);
            dialog.setVisible(true);
        }
    }

    private void infoDeliverer(int x, int y) {
        Deliverer deliverer = restaurantController.getDelivererByField(new Field(x, y));
        if (deliverer != null) {
            InfoDelivererDialog dialog = new InfoDelivererDialog(deliverer);
            dialog.setVisible(true);
        }
    }

    private void addCustomer(int x, int y) {
        // dodanie klienta w wybranym punkcie
        OccasionalCustomer customer = new OccasionalCustomer("name", "666555444",
                Integer.toString(x) + Integer.toString(y), "customer@gmail.com", x, y);
        restaurantController.addCustomer(customer);
    }

    // rysowanie mapy
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < mapController.getMap().SIZE; ++i) {
            for (int j = 0; j < mapController.getMap().SIZE; ++j) {
                Rectangle2D rectangle = new Rectangle2D.Double(i * (width + space), j * (width + space), width, width);
                Color color = fieldToColor(mapController.getMap().getField(i, j));
                g2d.setColor(color);
                g2d.fill(rectangle);
            }
        }
    }

    // zwraca kolor pola na mapie w zależności od typu pola
    private Color fieldToColor(FieldType field) {
        Color color = Color.WHITE;;

        switch (field) {
            case EMPTY:
                color = Color.WHITE;
                break;
            case ADDRESS:
                color = Color.LIGHT_GRAY;
                break;
            case ROAD:
                color = Color.GRAY;
                break;
            case RESTAURANT:
                color = Color.ORANGE;
                break;
            case DELIVERER:
                color = Color.BLUE;
                break;
            case CUSTOMER:
                color = Color.BLACK;
                break;
            case ORDER:
                color = Color.GREEN;
                break;
        }
        return color;
    }

    // uruchamianie metody rysującej co wyznaczony czas w milisekundach
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
        }
    }
}
