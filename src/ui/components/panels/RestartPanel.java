package ui.components.panels;

import objects.assets.lang.ResourcesLoader;

import javax.swing.*;

public class RestartPanel extends JPanel {

    public RestartPanel() {
        this.add(new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("restartPanel")));
    }
}
