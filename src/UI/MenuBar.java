package UI;

import Objects.MineField;
import Objects.MineHandler;

import javax.swing.*;

public class MenuBar {

    public static JMenuBar createMenuBar(MineHandler mineHandler, MineField mineField, FieldPanel fieldPanel) {

        JMenuBar menuBar = new JMenuBar();


        JMenu optionsMenu = new JMenu("Options");
        //Difficultly
        JMenu difficulty = new JMenu("Difficulty");
        JMenuItem easy = new JMenuItem("Easy");
        easy.addActionListener(e -> setSize(mineHandler, mineField, fieldPanel, 9, 9, 10));
        JMenuItem normal = new JMenuItem("Normal");
        normal.addActionListener(e -> setSize(mineHandler, mineField, fieldPanel, 16, 16, 40));
        JMenuItem hard = new JMenuItem("Hard");
        hard.addActionListener(e -> setSize(mineHandler, mineField, fieldPanel, 30, 16, 99));
        JMenuItem custom = new JMenuItem("Custom");
        custom.addActionListener(e -> {
            JTextField widthText = new JTextField();
            JTextField heightText = new JTextField();
            JTextField amountMinesText = new JTextField();
            final JComponent[] inputs = new JComponent[]{
                    new JLabel("Width"),
                    widthText,
                    new JLabel("Height"),
                    heightText,
                    new JLabel("Amount Mines"),
                    amountMinesText
            };
            int result = JOptionPane.showConfirmDialog(null, inputs, "Custom Size", JOptionPane.DEFAULT_OPTION);
            if (result == 0) {
                int width = Integer.parseInt(widthText.getText());
                int height = Integer.parseInt(heightText.getText());
                int amountMines = Integer.parseInt(amountMinesText.getText());
                if (width >= 8 && height >= 8 && width * height >= amountMines + 10 && width <= 30 && height <= 24 && amountMines >= 10 && amountMines <= 667) {
                    setSize(mineHandler, mineField, fieldPanel, width, height, amountMines);

                    //Debug
                    System.out.println("New Field with Height: " + height + ", Width: " + width + " and Mines: " + amountMines);
                }
            }
        });
        difficulty.add(easy);
        difficulty.add(normal);
        difficulty.add(hard);
        difficulty.add(custom);

        //Textures
        JMenu textures = new JMenu("Textures");
        JMenuItem background = new JMenuItem("Background");
        JMenuItem mine = new JMenuItem("Mine");
        JMenuItem cellTop = new JMenuItem("Cell Ceiling");
        JMenuItem flag = new JMenuItem("Flag");

        textures.add(background);
        textures.add(mine);
        textures.add(cellTop);
        textures.add(flag);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setToolTipText("Exit application");

        exitMenuItem.addActionListener((event) -> System.exit(0));


        optionsMenu.add(textures);
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
