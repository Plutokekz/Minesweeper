package ui.components.menubar;

import objects.Difficulty;
import objects.GameAction;
import objects.data.Difficulties;
import objects.data.Fonts;
import objects.exceptions.NoCoordinatesException;
import objects.type.ActionType;
import ui.components.FieldUI;
import ui.components.panels.CustomDifficultyDialogPanel;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private final FieldUI fieldUI;

    public MenuBar(FieldUI fieldUI) {
        this.fieldUI = fieldUI;
        setupGUI();
    }

    private void setupGUI() {

        this.setBorderPainted(false);


        JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setFont(Fonts.FontDefault);

        //Difficulties
        JMenu difficulties = new JMenu("Difficulties");
        difficulties.setFont(Fonts.FontDefault);

        //Difficulties: Easy
        JMenuItem easy = new JMenuItem("Easy");
        easy.setFont(Fonts.FontDefault);
        easy.addActionListener(e -> {
            try {
                fieldUI.update(new GameAction(ActionType.UpdateDifficulty, Difficulties.easy));
            } catch (NoCoordinatesException noCoordinatesException) {
                noCoordinatesException.printStackTrace();
            }
        });

        //Difficulties: Normal
        JMenuItem normal = new JMenuItem("Normal");
        normal.setFont(Fonts.FontDefault);
        normal.addActionListener(e -> {
            try {
                fieldUI.update(new GameAction(ActionType.UpdateDifficulty, Difficulties.normal));
            } catch (NoCoordinatesException noCoordinatesException) {
                noCoordinatesException.printStackTrace();
            }
        });

        //Difficulties: Hard
        JMenuItem hard = new JMenuItem("Hard");
        hard.setFont(Fonts.FontDefault);
        hard.addActionListener(e -> {
            try {
                fieldUI.update(new GameAction(ActionType.UpdateDifficulty, Difficulties.hard));
            } catch (NoCoordinatesException noCoordinatesException) {
                noCoordinatesException.printStackTrace();
            }
        });


        //Difficulties: Custom
        JMenuItem custom = new JMenuItem("Custom");
        custom.setFont(Fonts.FontDefault);
        custom.addActionListener(e -> {
            CustomDifficultyDialogPanel customDifficultyDialogPanel = new CustomDifficultyDialogPanel();
            JOptionPane.showMessageDialog(fieldUI.getGui(), customDifficultyDialogPanel, "Custom Size", JOptionPane.PLAIN_MESSAGE);
            Difficulty difficulty = customDifficultyDialogPanel.getDifficulty();

            try {
                fieldUI.update(new GameAction(ActionType.UpdateDifficulty, difficulty));
            } catch (NoCoordinatesException noCoordinatesException) {
                noCoordinatesException.printStackTrace();
            }

            //Debug
            System.out.println("New Field with Difficulty: " + difficulty.toString());

        });

        //Adding to Difficulties
        difficulties.add(easy);
        difficulties.add(normal);
        difficulties.add(hard);
        difficulties.add(custom);


        //Textures
        JMenu textures = new JMenu("Textures");
        textures.setFont(Fonts.FontDefault);
        //Texture: Background
        JMenuItem background = new JMenuItem("Background");
        background.setFont(Fonts.FontDefault);

        //Texture: Mine
        JMenuItem mine = new JMenuItem("Mine");
        mine.setFont(Fonts.FontDefault);

        //Texture: Top
        JMenuItem cellTop = new JMenuItem("Cell Ceiling");
        cellTop.setFont(Fonts.FontDefault);

        //Texture: Flag
        JMenuItem flag = new JMenuItem("Flag");
        flag.setFont(Fonts.FontDefault);

        //Adding to Textures Menu
        textures.add(background);
        textures.add(mine);
        textures.add(cellTop);
        textures.add(flag);


        //Exit option
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setToolTipText("Exit Minesweeper");
        exitMenuItem.setFont(Fonts.FontDefault);
        exitMenuItem.addActionListener((event) -> System.exit(0));

        //Adding to Option Menu
        optionsMenu.add(textures);
        optionsMenu.add(difficulties);
        optionsMenu.add(exitMenuItem);

        //Adding to Menu Bar
        this.add(optionsMenu);
    }

}
