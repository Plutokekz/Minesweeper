package ui.components.listeners;

import objects.GameAction;
import objects.MineFieldState;
import objects.assets.TileHandler;
import objects.assets.lang.ResourcesLoader;
import objects.base.Point;
import objects.type.ActionType;
import objects.type.GameState;
import ui.components.FieldUI;
import ui.components.panels.InformationPanel;
import ui.components.panels.LosPanel;
import ui.components.panels.MineFieldPanel;
import ui.components.panels.WinPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomMouseAdapter extends MouseAdapter {

    private final InformationPanel informationPanel;
    private final FieldUI fieldUI;
    //TODO add java docs

    public CustomMouseAdapter(InformationPanel informationPanel, FieldUI fieldUI) {
        this.informationPanel = informationPanel;
        this.fieldUI = fieldUI;
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
        } else if (e.getButton() == 4) {
            state = GameState.Win;
        }


        //Handel GameState
        switch (state) {
            case Lose:
                informationPanel.counter.stop();
                LosPanel losPanel = new LosPanel(informationPanel.getTimeCounterText());
                JOptionPane.showConfirmDialog(mineFieldPanel, losPanel, ResourcesLoader.RESOURCE_BUNDLE.getString("lost"), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(TileHandler.SPRITE_MINE));
                break;

            case Win:
                WinPanel winPanel = new WinPanel(informationPanel.getTimeCounterText());
                JOptionPane.showConfirmDialog(mineFieldPanel, winPanel, ResourcesLoader.RESOURCE_BUNDLE.getString("win"), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(TileHandler.SPRITE_MINE));

                String name = winPanel.getName();
                String difficulty = mineFieldPanel.getDifficultyString();
                int time = informationPanel.getTimeCounterInteger();

                fieldUI.showScoreBoard();
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

