package ui.components;

import objects.GameAction;
import objects.data.Difficulties;
import objects.exceptions.NoCoordinatesException;
import objects.type.ActionType;
import objects.type.GameState;
import ui.components.labels.ScoreObject;
import ui.components.listeners.CustomMouseAdapter;
import ui.components.panels.InformationPanel;
import ui.components.panels.MineFieldPanel;
import ui.components.panels.ScoreBoardPanel;
import util.ScoreBoardController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class FieldUI {

    private final JPanel gui;
    private final JPanel switchPanel;
    private final CardLayout cardLayout;
    private final ScoreBoardPanel scoreBoardPanel;
    private MineFieldPanel mineFieldPanel;
    private InformationPanel informationPanel;
    private ScoreBoardController scoreBoardController;


    public FieldUI() {
        cardLayout = new CardLayout();
        switchPanel = new JPanel(cardLayout);
        gui = new JPanel(new BorderLayout(3, 3));
        scoreBoardPanel = new ScoreBoardPanel();
        initializeGui();
    }

    public void showGame() {
        cardLayout.show(switchPanel, "game");
    }

    public void showScoreBoard() {
        ArrayList<ScoreObject> scoreObjects = new ArrayList<>();
        ScoreBoardController scoreBoardController = new ScoreBoardController();
        scoreBoardController.getScoreBoard(scoreObjects);
        cardLayout.show(switchPanel, "score");
        scoreBoardPanel.setListModel(scoreObjects);
    }

    public final void initializeGui() {

        // set up the main GUI
        gui.setBorder(new EmptyBorder(8, 8, 8, 8));

        // setting up PanelMineField
        mineFieldPanel = new MineFieldPanel(new CardLayout(10, 10), Difficulties.easy);
        mineFieldPanel.setBackground(new Color(142, 143, 144, 255));
        switchPanel.add(mineFieldPanel, "game");

        switchPanel.add(scoreBoardPanel, "score");
        gui.add(switchPanel);

        //setting up PanelTop
        informationPanel = new InformationPanel();
        gui.add(informationPanel, BorderLayout.PAGE_START);

        //Add Action Listener to Reset Button
        informationPanel.getResetButton().addActionListener(e -> {
            try {
                update(new GameAction(ActionType.Reset));
            } catch (NoCoordinatesException noCoordinatesException) {
                noCoordinatesException.printStackTrace();
            }
        });
        //Add Mouse Adapter to panelMineField
        mineFieldPanel.addMouseListener(new CustomMouseAdapter(informationPanel, this, scoreBoardController));
    }

    private void reset() throws NoCoordinatesException {
        //reset the field
        informationPanel.counter.stop();
        mineFieldPanel.update(new GameAction(ActionType.Reset));

        //Rest TopPanel after Thread as stopped
        informationPanel.reset();
        showGame();

    }


    public GameState update(GameAction gameAction) throws NoCoordinatesException {
        switch (gameAction.getType()) {
            case Reset:
                reset();
                return GameState.Reset;
            case UpdateDifficulty:
                GameState state = mineFieldPanel.update(gameAction);
                reset();
                return state;
            default:
                return GameState.Default;
        }

    }

    public final JComponent getGui() {
        return gui;
    }

}
