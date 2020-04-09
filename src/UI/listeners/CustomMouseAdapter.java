package UI.listeners;

import Objects.BaseObjects.Point;
import Objects.GameState;
import UI.panel.PanelHUD;
import UI.panel.PanelMineField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomMouseAdapter extends MouseAdapter {

    private final PanelMineField panelMineField;
    private final PanelHUD panelHUD;

    public CustomMouseAdapter(PanelMineField panelMineField, PanelHUD panelHUD) {
        this.panelMineField = panelMineField;
        this.panelHUD = panelHUD;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        GameState state = GameState.Default;

        Dimension panelDimension = panelMineField.calculatePanelMineFieldDimension(panelMineField.calculateMaxCellSize());
        Point offsetPoint = panelMineField.calculateOffsetPoint(panelDimension);


        //Calculate the x and y indexes for the Field
        // => Subtract the x and y difference from the Mouse Coordinates and divide it by the Calculate Image Size which gets divided
        int y = (e.getY() - offsetPoint.getY()) / (int) (panelDimension.getHeight() / panelMineField.getMineField().getColumns());
        int x = (e.getX() - offsetPoint.getX()) / (int) (panelDimension.getWidth() / panelMineField.getMineField().getRows());

        // Left Click
        if (e.getButton() == 1) {
            state = panelMineField.getMineField().leftClick(x, y);
            if (state == GameState.FistClick) {
                System.out.println("First Click: " + state);
                panelHUD.setRemainingMines(panelMineField.getMineField().getMinesRemaining());
                //Start Clock
            }
        }
        // Debug Show entire Field by clicking the Mousewheel
        else if (e.getButton() == 2) {
            panelMineField.getMineField().showField();
        }
        // Right Click
        else if (e.getButton() == 3) {
            state = panelMineField.getMineField().rightClick(x, y);
            // update minesRemaining
            panelHUD.setRemainingMines(panelMineField.getMineField().getMinesRemaining());
        }

        // Update The drawn Image
        panelMineField.revalidate();
        panelMineField.repaint();


        //Handel GameState
        switch (state) {
            case Lose:
                JOptionPane.showConfirmDialog(null, new JLabel("You Lost !\n    :("), "You Lost !", JOptionPane.DEFAULT_OPTION);
                break;

            case Win:
                JTextField name = new JTextField();
                final JComponent[] inputs = new JComponent[]{
                        new JLabel("Your Score: "),
                        new JLabel("Enter your name: "),
                        name
                };
                JOptionPane.showConfirmDialog(null, inputs, "You Won !", JOptionPane.DEFAULT_OPTION);
                break;
        }

        // Debug
        System.out.println("Clicked Cell: (x: " + x + " y: " + y + ")");
    }
}

