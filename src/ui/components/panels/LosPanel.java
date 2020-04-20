package ui.components.panels;

import objects.assets.lang.ResourcesLoader;
import objects.data.Fonts;

import javax.swing.*;
import java.awt.*;

public class LosPanel extends JPanel {

    public LosPanel(String time) {
        super(new GridLayout(1, 0));
        JLabel timeString = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("timeLabel"));
        timeString.setFont(Fonts.FontDefault);
        this.add(timeString);
        JLabel timeTime = new JLabel(time);
        timeTime.setFont(Fonts.FontDefault);
        this.add(timeTime);
    }

}
