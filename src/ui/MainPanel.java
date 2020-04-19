package ui;

import objects.MyProperties;
import objects.assets.TileHandler;
import objects.assets.lang.ResourcesLoader;
import objects.data.Fonts;
import ui.components.FieldUI;
import ui.components.menubar.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Locale;

public class MainPanel {

    public static void main(String[] args) throws IOException {
        TileHandler.loadDefaultSprites();
        MyProperties.loadMyProperties();
        Locale.setDefault(MyProperties.getLocale());

        Runnable r = () -> {
            FieldUI fieldUI = new FieldUI();
            JFrame f = new JFrame(ResourcesLoader.RESOURCE_BUNDLE.getString("minePanelTitle"));
            f.setFont(Fonts.FontDefault);
            f.add(fieldUI.getGui());
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationByPlatform(true);
            f.setIconImage(TileHandler.SPRITE_MINE);
            //Add Menu Bar

            f.setJMenuBar(new MenuBar(fieldUI));
            f.pack();
            f.setMinimumSize(new Dimension(1000, 720));
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }

}
