package ui.components;

import objects.Difficulties;
import objects.Difficulty;
import objects.Fonts;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class CustomDifficultyDialog extends JPanel {

    //TODO NumberFormatter is not the right thing for only allowing number in the textfield
    private final JFormattedTextField widthText = new JFormattedTextField(new NumberFormatter(NumberFormat.getInstance()));
    private final JFormattedTextField heightText = new JFormattedTextField(new NumberFormatter(NumberFormat.getInstance()));
    private final JFormattedTextField amountMinesText = new JFormattedTextField(new NumberFormatter(NumberFormat.getInstance()));

    private final JLabel widthLabel = new JLabel("width:");
    private final JLabel heightLabel = new JLabel("height:");
    private final JLabel amountMinesLabel = new JLabel("mines:");

    public CustomDifficultyDialog() {
        super(new GridLayout(3, 3));
        setupGui();
    }

    private void setupGui() {
        widthLabel.setFont(Fonts.FontDefault);
        widthText.setFont(Fonts.FontDefault);

        heightLabel.setFont(Fonts.FontDefault);
        heightText.setFont(Fonts.FontDefault);

        amountMinesLabel.setFont(Fonts.FontDefault);
        amountMinesText.setFont(Fonts.FontDefault);


        this.add(widthLabel);
        this.add(widthText);
        this.add(heightLabel);
        this.add(heightText);
        this.add(amountMinesLabel);
        this.add(amountMinesText);
    }

    public Difficulty getDifficulty() {
        if (!widthText.getText().isEmpty() && !heightText.getText().isEmpty() && !amountMinesText.getText().isEmpty()) {
            int width = 9, height = 9, amountMines = 10;
            try {
                width = Integer.parseInt(widthText.getText());
                height = Integer.parseInt(heightText.getText());
                amountMines = Integer.parseInt(amountMinesText.getText());
            } catch (NumberFormatException e) {
                System.out.println("There was a problem with your numbers");
            }

            if (width >= 8 && height >= 8 && width * height >= amountMines + 10 && width <= 30 && height <= 24 && amountMines >= 10 && amountMines <= 667) {
                return new Difficulty(width, height, amountMines);
            }

        }

        return Difficulties.easy;
    }

}
