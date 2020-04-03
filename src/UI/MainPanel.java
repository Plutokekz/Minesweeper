package UI;

import Objects.MineField;
import Objects.MineHandler;
import Objects.Temp.Sprites;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel {


    public static void main(String[] args) {

        int width = 20;
        int height = 20;
        int amountMines = 10;


        Runnable r = () -> {

            try {
                Sprites.loadSprites();
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

            f.pack();
            f.setMinimumSize(new Dimension(720, 1280));
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}
