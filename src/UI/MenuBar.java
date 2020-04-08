package UI;

import Objects.MineField;
import Objects.MineHandler;

import javax.swing.*;

public class MenuBar {

    public static JMenuBar createMenuBar(MineHandler mineHandler, MineField mineField, FieldPanel fieldPanel) {

        JMenuBar menuBar = new JMenuBar();


        JMenu optionsMenu = new JMenu("Options");
        JMenu difficulty = new JMenu("Difficulty");

        JMenuItem easy = new JMenuItem("Easy");
        easy.addActionListener(e -> setSize(mineHandler, mineField, fieldPanel, 9, 9, 10));
        JMenuItem normal = new JMenuItem("Normal");
        normal.addActionListener(e -> setSize(mineHandler, mineField, fieldPanel, 16, 16, 40));
        JMenuItem hard = new JMenuItem("Hard");
        hard.addActionListener(e -> setSize(mineHandler, mineField, fieldPanel, 30, 16, 99));
        JMenuItem custom = new JMenuItem("Custom");

        difficulty.add(easy);
        difficulty.add(normal);
        difficulty.add(hard);
        difficulty.add(custom);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setToolTipText("Exit application");

        exitMenuItem.addActionListener((event) -> System.exit(0));


        optionsMenu.add(difficulty);
        optionsMenu.add(exitMenuItem);


        menuBar.add(optionsMenu);

        return menuBar;
    }

    private static void setSize(MineHandler mineHandler, MineField mineField, FieldPanel fieldPanel, int width, int height, int amountMines) {
        mineHandler.setHeight(height);
        mineHandler.setWidth(width);
        mineHandler.setAmountMines(amountMines);
        mineField.setHeight(height);
        mineField.setWidth(width);
        fieldPanel.setColumns(height);
        fieldPanel.setRows(width);
        fieldPanel.rest();
    }

}
