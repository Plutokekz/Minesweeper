package UI;

import Objects.assets.TileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel {


    public static void main(String[] args) {
        Runnable r = () -> {

            try {
                TileHandler.loadDefaultSprites();
            } catch (IOException e) {
                e.printStackTrace();
            }


            FieldPanel cg = new FieldPanel();

            JFrame f = new JFrame("MineSweeper");
            f.add(cg.getGui());
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationByPlatform(true);

            //Add Menu Bar
            f.setJMenuBar(MenuBar.createMenuBar(cg));

            f.pack();
            f.setMinimumSize(new Dimension(1000, 720));
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}
