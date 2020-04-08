package UI;

import Objects.MineField;
import Objects.MineHandler;
import Objects.assets.TileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel {


    public static void main(String[] args) {
        int width = 9, height = 9, amountMines = 10;
        Runnable r = () -> {

            try {
                TileHandler.loadDefaultSprites();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MineHandler mineHandler = new MineHandler(width, height, amountMines);
            MineField mineField = new MineField(mineHandler, height, width);


            FieldPanel cg = new FieldPanel(width, height, mineField);

            JFrame f = new JFrame("MineSweeper");
            f.add(cg.getGui());
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationByPlatform(true);
            JMenuBar menu = new JMenuBar();
            JMenu options = new JMenu("Options");
            JMenuItem size = new JMenuItem("size");
            options.add(size);
            menu.add(options);
            f.setJMenuBar(MenuBar.createMenuBar(mineHandler, mineField, cg));

            f.pack();
            f.setMinimumSize(new Dimension(1000, 720));
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}
