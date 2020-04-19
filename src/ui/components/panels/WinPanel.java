package ui.components.panels;

import objects.assets.lang.ResourcesLoader;

import javax.swing.*;
import java.awt.*;

public class WinPanel extends JPanel {

    private JTextField name;

    public WinPanel(String time) {
        super(new GridLayout(2, 1));
        setupGui(time);
    }

    private void setupGui(String time) {
        name = new JTextField();
        JLabel timeLabel = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("timeLabel"));
        JLabel timeOnlyLabel = new JLabel(time);
        JLabel enterName = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("enterNameLabel"));
        this.add(timeLabel);
        this.add(timeOnlyLabel);
        this.add(enterName);
        this.add(name);
    }

    public String getName() {
        if (!name.getText().isEmpty()) {
            return name.getText();
        }
        return null;
    }

}
