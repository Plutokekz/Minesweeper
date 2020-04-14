package ui.components.panels;

import objects.Difficulty;
import objects.assets.lang.ResourcesLoader;
import objects.data.Difficulties;
import objects.data.Fonts;
import ui.components.textfield.JNumberTextField;

import javax.swing.*;
import java.awt.*;

public class CustomDifficultyDialogPanel extends JPanel {

    private final JNumberTextField widthText = new JNumberTextField();
    private final JNumberTextField heightText = new JNumberTextField();
    private final JNumberTextField amountMinesText = new JNumberTextField();

    private final JLabel widthLabel = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("widthLabel") + ":");
    private final JLabel heightLabel = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("heightLabel") + ":");
    private final JLabel amountMinesLabel = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("mineLabel") + ":");

    public CustomDifficultyDialogPanel() {
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

    /**
     * Returns a Difficulty from the DialogPanel if the inputs are not valid it returns an easy difficulty
     *
     * @see Difficulties
     */
    public Difficulty getDifficulty() {
        if (!widthText.getText().isEmpty() && !heightText.getText().isEmpty() && !amountMinesText.getText().isEmpty()) {
            int width, height, amountMines;

            width = widthText.getNumber();
            height = heightText.getNumber();
            amountMines = amountMinesText.getNumber();

            if (width >= 8 && height >= 8 && width * height >= amountMines + 10 && width <= 30 && height <= 24 && amountMines >= 10 && amountMines <= 667) {
                return new Difficulty(width, height, amountMines);
            }

        }

        return Difficulties.easy;
    }

}
