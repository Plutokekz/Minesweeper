package ui.components.panels;

import objects.assets.lang.ResourcesLoader;
import objects.data.Fonts;
import ui.components.runnables.Counter;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {

    //TODO Add java docs

    private JButton resetButton;
    private JLabel timeCounter, minesRemainingCounter;
    public final Counter counter;
    private Integer time = 0;

    public InformationPanel() {
        super(new FlowLayout(FlowLayout.CENTER, 100, 0)); //TODO make the TopPanel size change dynamically
        this.counter = new Counter(this);
        setupUI();
    }

    private void setupUI() {
        resetButton = new JButton(ResourcesLoader.RESOURCE_BUNDLE.getString("restartButton"));
        resetButton.setFont(Fonts.FontDefault);
        resetButton.setFocusPainted(false);
        minesRemainingCounter = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("remainingMinesLabel") + ": 00");
        minesRemainingCounter.setFont(Fonts.FontDefault);
        timeCounter = new JLabel("00:00");
        timeCounter.setFont(Fonts.FontDefault);

        this.add(minesRemainingCounter, FlowLayout.LEFT);
        this.add(resetButton, FlowLayout.CENTER);
        this.add(timeCounter, FlowLayout.RIGHT);

    }

    public void setRemainingMines(int remainingMines) {
        String remainingMinesString = String.valueOf(remainingMines);
        if (remainingMines < 0 && remainingMines / 10 == 0) {
            remainingMinesString = "-0" + Math.abs(remainingMines);
        } else if (remainingMines / 10 == 0) {
            remainingMinesString = "0" + remainingMinesString;
        }
        minesRemainingCounter.setText(ResourcesLoader.RESOURCE_BUNDLE.getString("remainingMinesLabel") + ": " + remainingMinesString);
    }

    public void setTime(int seconds) {
        time = seconds;
        int min = seconds / 60;
        int sec = seconds % 60;
        String minutesString = Integer.toString(min);
        String secondsString = Integer.toString(sec);
        if (min / 10 == 0) minutesString = "0" + minutesString;
        if (sec / 10 == 0) secondsString = "0" + secondsString;
        timeCounter.setText(minutesString + ":" + secondsString);
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public String getTimeCounterText() {
        return timeCounter.getText();
    }

    public int getTimeCounterInteger() {
        return time;
    }

    public void reset() {
        minesRemainingCounter.setText(ResourcesLoader.RESOURCE_BUNDLE.getString("remainingMinesLabel") + ": 00");
        timeCounter.setText("00:00");
    }

}
