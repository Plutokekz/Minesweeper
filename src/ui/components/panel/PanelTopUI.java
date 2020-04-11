package ui.components.panel;

import objects.Fonts;
import ui.components.Counter;

import javax.swing.*;
import java.awt.*;

public class PanelTopUI extends JPanel {

    private JButton resetButton;
    private JLabel timeCounter, minesRemainingCounter;
    public final Counter counter;

    public PanelTopUI() {
        super(new FlowLayout(FlowLayout.CENTER, 100, 0)); //TODO make the TopPanel size change dynamically
        this.counter = new Counter(this);
        setupUI();
    }

    private void setupUI() {
        resetButton = new JButton("Restart");
        resetButton.setFont(Fonts.FontDefault);
        resetButton.setFocusPainted(false);
        minesRemainingCounter = new JLabel("Mines: 00");
        minesRemainingCounter.setFont(Fonts.FontDefault);
        timeCounter = new JLabel("00:00");
        timeCounter.setFont(Fonts.FontDefault);

        this.add(minesRemainingCounter, FlowLayout.LEFT);
        this.add(resetButton, FlowLayout.CENTER);
        this.add(timeCounter, FlowLayout.RIGHT);

    }

    public void setRemainingMines(int remainingMines) {
        String remainingMinesString = String.valueOf(remainingMines);
        if (remainingMines / 10 == 0) {
            remainingMinesString = "0" + remainingMinesString;
        }
        minesRemainingCounter.setText("Mines: " + remainingMinesString);
    }

    public void setTime(int seconds) {
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

    public void reset() {
        minesRemainingCounter.setText("Mines: 00");
        timeCounter.setText("00:00");
    }

}
