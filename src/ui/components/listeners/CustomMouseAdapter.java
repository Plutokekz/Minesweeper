package ui.components.listeners;

import objects.GameAction;
import objects.MineFieldState;
import objects.assets.TileHandler;
import objects.base.Point;
import objects.type.ActionType;
import objects.type.GameState;
import ui.components.panels.InformationPanel;
import ui.components.panels.MineFieldPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomMouseAdapter extends MouseAdapter {

    private final InformationPanel informationPanel;

    //TODO add java docs

    public CustomMouseAdapter(InformationPanel informationPanel) {
        this.informationPanel = informationPanel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        MineFieldPanel mineFieldPanel = (MineFieldPanel) e.getSource();


        GameState state = GameState.Default;

        MineFieldState mineFieldState = mineFieldPanel.getMineFieldState();
        Dimension panelDimension = mineFieldPanel.calculatePanelMineFieldDimension(mineFieldPanel.calculateMaxCellSize());
        Point offsetPoint = mineFieldPanel.calculateOffsetPoint(panelDimension);


        //Calculate the x and y indexes for the Field
        // => Subtract the x and y difference from the Mouse Coordinates and divide it by the Calculate Image Size which gets divided
        int y = (e.getY() - offsetPoint.getY()) / (int) (panelDimension.getHeight() / mineFieldState.getHeight());
        int x = (e.getX() - offsetPoint.getX()) / (int) (panelDimension.getWidth() / mineFieldState.getWidth());

        // Left Click
        if (e.getButton() == 1) {
            state = leftClick(mineFieldPanel, x, y);
        }
        // Debug Show entire Field by clicking the Mousewheel
        else if (e.getButton() == 2) {
            mineFieldPanel.debugShowAll();
        }
        // Right Click
        else if (e.getButton() == 3) {
            state = rightClick(mineFieldPanel, x, y);
        }


        //Handel GameState
        switch (state) {
            case Lose:
                informationPanel.counter.stop();
                JOptionPane.showConfirmDialog(mineFieldPanel, new JLabel("Time played: " + informationPanel.getTimeCounterText()), "You Lost !", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(TileHandler.SPRITE_MINE));
                break;

            case Win:
                informationPanel.counter.stop();
                JTextField name = new JTextField();
                final JComponent[] inputs = new JComponent[]{
                        new JLabel("Your Time: " + informationPanel.getTimeCounterText()),
                        new JLabel("Enter your name: "),
                        name
                };
                JOptionPane.showConfirmDialog(mineFieldPanel, inputs, "You Won !", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(TileHandler.SPRITE_MINE));
                break;
        }

        // Debug
        System.out.println("Clicked Cell: (x: " + x + " y: " + y + ")");
    }

    private GameState leftClick(MineFieldPanel mineFieldPanel, int x, int y) {
        GameState state = mineFieldPanel.update(new GameAction(ActionType.LeftMouseButton, x, y));
        if (state == GameState.FistClick) {
            firstClick(informationPanel, mineFieldPanel.getMineFieldState().getAmountMines());
        }
        return state;
    }

    private void firstClick(InformationPanel informationPanel, int remainingMines) {
        informationPanel.setRemainingMines(remainingMines);
        //Start Clock
        informationPanel.counter.start();
        Thread counterThread = new Thread(informationPanel.counter);
        counterThread.setDaemon(true);
        counterThread.start();
    }

    private GameState rightClick(MineFieldPanel mineFieldPanel, int x, int y) {
        GameState state = mineFieldPanel.update(new GameAction(ActionType.RightMouseButton, x, y));
        // update minesRemaining
        informationPanel.setRemainingMines(mineFieldPanel.getMineFieldState().getAmountMines());
        return state;
    }

}

