package UI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel {
    public static void main(String[] args) {
        Runnable r = () -> {

            FieldPanel cg = null;
            try {
                cg = new FieldPanel(10, 10);
            } catch (IOException e) {
                e.printStackTrace();
            }

            JFrame f = new JFrame("MineSweeper");
            assert cg != null;
            f.add(cg.getGui());
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationByPlatform(true);

            f.pack();
            f.setMinimumSize(new Dimension(720, 1280));
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}
