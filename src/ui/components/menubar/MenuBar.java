package ui.components.menubar;

import objects.assets.TileHandler;
import objects.assets.lang.ResourcesLoader;
import objects.data.Difficulties;
import objects.data.Difficulty;
import objects.data.Fonts;
import objects.data.GameAction;
import objects.exceptions.NoCoordinatesException;
import objects.type.ActionType;
import objects.util.MyProperties;
import ui.components.FieldUI;
import ui.components.panels.CustomDifficultyDialogPanel;
import ui.components.panels.RestartPanel;

import javax.swing.*;
import java.io.IOException;

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

        JMenuItem scoreBord = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("scoreBoardLabel"));
        scoreBord.setFont(Fonts.FontDefault);
        scoreBord.addActionListener(e -> fieldUI.showScoreBoard());

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
        JMenu language = new JMenu(ResourcesLoader.RESOURCE_BUNDLE.getString("language"));
        language.setFont(Fonts.FontDefault);

        //Texture: Background
        JMenuItem german = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("german"));
        german.setFont(Fonts.FontDefault);
        german.addActionListener(e -> {
            try {
                MyProperties.setLocal("de");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            JOptionPane.showConfirmDialog(null, new RestartPanel(), ResourcesLoader.RESOURCE_BUNDLE.getString("info"), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(TileHandler.SPRITE_MINE));
        });

        //Texture: Mine
        JMenuItem english = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("english"));
        english.setFont(Fonts.FontDefault);
        english.addActionListener(e -> {
            try {
                MyProperties.setLocal("en");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            JOptionPane.showConfirmDialog(null, new RestartPanel(), ResourcesLoader.RESOURCE_BUNDLE.getString("info"), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(TileHandler.SPRITE_MINE));
        });

        //Texture: Top
        JMenuItem korean = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("korean"));
        korean.setFont(Fonts.FontDefault);
        korean.addActionListener(e -> {
            try {
                MyProperties.setLocal("ko");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            JOptionPane.showConfirmDialog(null, new RestartPanel(), ResourcesLoader.RESOURCE_BUNDLE.getString("info"), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(TileHandler.SPRITE_MINE));
        });


        //Adding to Textures Menu
        language.add(english);
        language.add(german);
        language.add(korean);


        //Exit option
        JMenuItem exitMenuItem = new JMenuItem(ResourcesLoader.RESOURCE_BUNDLE.getString("exitOptionLabel"));
        exitMenuItem.setToolTipText(ResourcesLoader.RESOURCE_BUNDLE.getString("exitMinesweeperToolTipLabel"));
        exitMenuItem.setFont(Fonts.FontDefault);
        exitMenuItem.addActionListener((event) -> System.exit(0));

        //Adding to Option Menu
        optionsMenu.add(scoreBord);
        optionsMenu.add(language);
        optionsMenu.add(difficulties);
        optionsMenu.add(exitMenuItem);

        //Adding to Menu Bar
        this.add(optionsMenu);
    }

}
