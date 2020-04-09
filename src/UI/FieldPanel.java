package UI;

import Objects.Difficulties;
import Objects.MineField;
import UI.listeners.CustomMouseAdapter;
import UI.panel.PanelHUD;
import UI.panel.PanelMineField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FieldPanel {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private PanelMineField panelMineField;
    private PanelHUD panelHUD;

    /**
     *
     **/
    FieldPanel(int columns, int rows, int amountMines) {
        this.mineField = new MineField(columns, rows, amountMines);
        initializeGui();
    }

    private final MineField mineField;

    FieldPanel() {
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
        panelHUD = new PanelHUD();
        gui.add(panelHUD, BorderLayout.PAGE_START);

        //Add Action Listener to Reset Button
        panelHUD.getResetButton().addActionListener(e -> rest());
        //Add Mouse Adapter to panelMineField
        panelMineField.addMouseListener(new CustomMouseAdapter(panelMineField, panelHUD));
    }

    public void rest() {
        //reset the field
        mineField.reset();
        //update the mine label
        panelHUD.setRemainingMines(mineField.getMinesRemaining());
        //redraw everything
        panelMineField.revalidate();
        panelMineField.repaint();

    }

    public final JComponent getGui() {
        return gui;
    }

}
