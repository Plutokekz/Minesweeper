package ui.components.menubar;

import objects.Difficulty;
import objects.GameAction;
import objects.assets.lang.ResourcesLoader;
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


        JMenu optionsMenu = new JMenu(ResourcesLoader.RESOURCE_BUNDLE.getString("optionsLabel"));
        optionsMenu.setFont(Fonts.FontDefault);

        //Difficulties
        JMenu difficulties = new JMenu(ResourcesLoader.RESOURCE_BUNDLE.getString("difficultyLabel"));
        difficulties.setFont(Fonts.FontDefault);

        //Difficulties: Easy
        JMenuItem easy = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("easyLabel"));
        easy.setFont(Fonts.FontDefault);
        easy.addActionListener(e -> {
            try {
                fieldUI.update(new GameAction(ActionType.UpdateDifficulty, Difficulties.easy));
            } catch (NoCoordinatesException noCoordinatesException) {
                noCoordinatesException.printStackTrace();
            }
        });

        //Difficulties: Normal
        JMenuItem normal = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("normalLabel"));
        normal.setFont(Fonts.FontDefault);
        normal.addActionListener(e -> {
            try {
                fieldUI.update(new GameAction(ActionType.UpdateDifficulty, Difficulties.normal));
            } catch (NoCoordinatesException noCoordinatesException) {
                noCoordinatesException.printStackTrace();
            }
        });

        //Difficulties: Hard
        JMenuItem hard = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("hardLabel"));
        hard.setFont(Fonts.FontDefault);
        hard.addActionListener(e -> {
            try {
                fieldUI.update(new GameAction(ActionType.UpdateDifficulty, Difficulties.hard));
            } catch (NoCoordinatesException noCoordinatesException) {
                noCoordinatesException.printStackTrace();
            }
        });


        //Difficulties: Custom
        JMenuItem custom = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("customLabel"));
        custom.setFont(Fonts.FontDefault);
        custom.addActionListener(e -> {
            CustomDifficultyDialogPanel customDifficultyDialogPanel = new CustomDifficultyDialogPanel();
            JOptionPane.showMessageDialog(fieldUI.getGui(), customDifficultyDialogPanel, ResourcesLoader.RESOURCE_BUNDLE.getString("customSizeLabel"), JOptionPane.PLAIN_MESSAGE);
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
        JMenu textures = new JMenu(ResourcesLoader.RESOURCE_BUNDLE.getString("texturesLabel"));
        textures.setFont(Fonts.FontDefault);
        //Texture: Background
        JMenuItem background = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("backgroundOptionLabel"));
        background.setFont(Fonts.FontDefault);

        //Texture: Mine
        JMenuItem mine = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("mineOptionLabel"));
        mine.setFont(Fonts.FontDefault);

        //Texture: Top
        JMenuItem cellTop = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("cellCellingOptionLabel"));
        cellTop.setFont(Fonts.FontDefault);

        //Texture: Flag
        JMenuItem flag = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("flagOptionLabel"));
        flag.setFont(Fonts.FontDefault);

        //Adding to Textures Menu
        textures.add(background);
        textures.add(mine);
        textures.add(cellTop);
        textures.add(flag);


        //Exit option
        JMenuItem exitMenuItem = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("exitOptionLabel"));
        exitMenuItem.setToolTipText(ResourcesLoader.RESOURCE_BUNDLE.getString("exitMinesweeperToolTipLabel"));
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
