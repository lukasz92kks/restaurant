package pl.krawczyk.restaurant;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import pl.krawczyk.restaurant.view.MainFrame;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new MainFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
