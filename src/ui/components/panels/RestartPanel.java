package ui.components.panels;

import objects.assets.lang.ResourcesLoader;
import objects.data.Fonts;

import javax.swing.*;

public class RestartPanel extends JPanel {

    public RestartPanel() {
        JLabel label = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("restartPanel"));
        label.setFont(Fonts.FontDefault);
        this.add(label);
    }
}
