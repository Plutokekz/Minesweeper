package ui.components;

import objects.Difficulties;
import objects.Difficulty;
import objects.MineField;
import ui.components.listeners.CustomMouseAdapter;
import ui.components.panel.PanelMineField;
import ui.components.panel.PanelTopUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FieldPanel {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private PanelMineField panelMineField;
    private PanelTopUI panelTopUI;

    /**
     *
     **/
    FieldPanel(int columns, int rows, int amountMines) {
        this.mineField = new MineField(columns, rows, amountMines);
        initializeGui();
    }

    private final MineField mineField;

    public FieldPanel() {
        this.mineField = new MineField(Difficulties.easy);
        initializeGui();
    }

    public MineField getMineField() {
        return mineField;
    }

    public final void initializeGui() {

        // set up the main GUI
        gui.setBorder(new EmptyBorder(8, 8, 8, 8));

        // setting up PanelMineField
        panelMineField = new PanelMineField(new CardLayout(10, 10), mineField);
        Color ochre = new Color(142, 143, 144, 255);
        panelMineField.setBackground(ochre);
        gui.add(panelMineField);

        //setting up PanelTop
        panelTopUI = new PanelTopUI();
        gui.add(panelTopUI, BorderLayout.PAGE_START);

        //Add Action Listener to Reset Button
        panelTopUI.getResetButton().addActionListener(e -> rest());
        //Add Mouse Adapter to panelMineField
        panelMineField.addMouseListener(new CustomMouseAdapter(panelMineField, panelTopUI));
    }

    public void rest() {
        //reset the field
        mineField.reset();
        //update the mine label
        panelTopUI.setRemainingMines(mineField.getMinesRemaining());
        //redraw everything
        panelMineField.revalidate();
        panelMineField.repaint();

    }

    public void setDifficulty(Difficulty difficulty) {
        panelMineField.setDifficulty(difficulty);
        rest();
    }

    public final JComponent getGui() {
        return gui;
    }

}
