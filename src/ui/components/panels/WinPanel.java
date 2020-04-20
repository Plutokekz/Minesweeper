package ui.components.panels;

import objects.assets.lang.ResourcesLoader;
import objects.data.Fonts;

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
        timeLabel.setFont(Fonts.FontDefault);
        JLabel timeOnlyLabel = new JLabel(time);
        timeOnlyLabel.setFont(Fonts.FontDefault);
        JLabel enterName = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("enterNameLabel"));
        enterName.setFont(Fonts.FontDefault);
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
