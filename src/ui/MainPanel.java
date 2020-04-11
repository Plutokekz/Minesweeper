package ui;

import objects.Fonts;
import objects.assets.TileHandler;
import ui.components.FieldPanel;
import ui.components.MenuBar;

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
                System.exit(-1);
            }

            FieldPanel cg = new FieldPanel();

            JFrame f = new JFrame("");
            f.setFont(Fonts.FontDefault);
            f.setTitle("Minesweeper");
            f.add(cg.getGui());
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationByPlatform(true);
            f.setIconImage(TileHandler.SPRITE_MINE);

            //Add Menu Bar
            f.setJMenuBar(new MenuBar(cg));

            f.pack();
            f.setMinimumSize(new Dimension(1000, 720));
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}
