package UI;

import Objects.MineField;
import Objects.MineHandler;
import Objects.assets.TileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel {


    public static void main(String[] args) {
        int width = 0, height = 0, amountMines = 0;
        int key = 0;
        if (args.length > 0) {
            key = Integer.parseInt(args[0]);
        }
        switch (key) {
            case 1:
                width = 16;
                height = 16;
                amountMines = 40;
                break;
            case 2:
                width = 30;
                height = 16;
                amountMines = 99;
                break;
            default:
                width = 9;
                height = 9;
                amountMines = 10;
        }


        int finalWidth = width;
        int finalHeight = height;
        int finalAmountMines = amountMines;
        Runnable r = () -> {

            try {
                TileHandler.loadDefaultSprites();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MineHandler mineHandler = new MineHandler(finalWidth, finalHeight, finalAmountMines);
            MineField mineField = new MineField(mineHandler, finalHeight, finalWidth);


            FieldPanel cg = new FieldPanel(finalWidth, finalHeight, mineField);

            JFrame f = new JFrame("MineSweeper");
            f.add(cg.getGui());
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationByPlatform(true);
            JMenuBar menu = new JMenuBar();
            menu.add(new JMenu("Options"));
            f.setJMenuBar(menu);


            f.pack();
            f.setMinimumSize(new Dimension(1000, 1280));
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}
