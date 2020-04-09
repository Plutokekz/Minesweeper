package UI.panel;

import javax.swing.*;
import java.awt.*;

public class PanelHUD extends JPanel {

    private JButton resetButton;
    private JLabel timeCounter, minesRemainingCounter;

    public PanelHUD() {
        super(new FlowLayout(FlowLayout.CENTER, 100, 0)); //TODO make the TopPanel size change dynamically
        setupUI();
    }

    private void setupUI() {
        resetButton = new JButton("Restart");
        minesRemainingCounter = new JLabel("Mines: 0");
        timeCounter = new JLabel("00:00");

        this.add(minesRemainingCounter, FlowLayout.LEFT);
        this.add(resetButton, FlowLayout.CENTER);
        this.add(timeCounter, FlowLayout.RIGHT);

    }

    public void setRemainingMines(int remainingMines) {
        minesRemainingCounter.setText("Mines: " + remainingMines);
    }

    public void setTime(int seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;
        String minutesString = Integer.toString(min);
        String secondsString = Integer.toString(sec);
        if (min / 10 == 0) minutesString = "0" + minutesString;
        if (min / 10 == 0) secondsString = "0" + secondsString;
        timeCounter.setText(minutesString + ":" + secondsString);
    }

    public JButton getResetButton() {
        return resetButton;
    }

}
