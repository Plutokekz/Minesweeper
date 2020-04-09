package UI;

import Objects.Difficulties;
import Objects.Difficulty;

import javax.swing.*;

public class MenuBar {

    public static JMenuBar createMenuBar(FieldPanel fieldPanel) {

        JMenuBar menuBar = new JMenuBar();


        JMenu optionsMenu = new JMenu("Options");
        //Difficultly
        JMenu difficulty = new JMenu("Difficulty");
        JMenuItem easy = new JMenuItem("Easy");
        easy.addActionListener(e -> setSize(fieldPanel, Difficulties.easy));
        JMenuItem normal = new JMenuItem("Normal");
        normal.addActionListener(e -> setSize(fieldPanel, Difficulties.normal));
        JMenuItem hard = new JMenuItem("Hard");
        hard.addActionListener(e -> setSize(fieldPanel, Difficulties.hard));
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
                    setSize(fieldPanel, new Difficulty(width, height, amountMines));

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

    private static void setSize(FieldPanel fieldPanel, Difficulty difficulty) {
        fieldPanel.getMineField().setDifficulty(difficulty);
        fieldPanel.rest();
    }

}
