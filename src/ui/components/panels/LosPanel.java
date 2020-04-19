package ui.components.panels;

import objects.assets.lang.ResourcesLoader;

import javax.swing.*;
import java.awt.*;

public class LosPanel extends JPanel {

    public LosPanel(String time) {
        super(new GridLayout(1, 0));
        this.add(new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("timeLabel")));
        this.add(new JLabel(time));
    }

}
