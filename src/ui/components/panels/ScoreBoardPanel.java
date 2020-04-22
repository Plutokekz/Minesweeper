package ui.components.panels;

import objects.assets.lang.ResourcesLoader;
import objects.data.Fonts;
import objects.util.ScoreBoardController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScoreBoardPanel extends JPanel {

    private final DefaultListModel<ScoreObjectPanel> defaultListModel;
    private final ScoreBoardController scoreBoardController;

    public ScoreBoardPanel() {
        super(new BorderLayout(3, 3));
        scoreBoardController = new ScoreBoardController();
        this.defaultListModel = new DefaultListModel<>();
        JLabel name = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("name"));
        name.setFont(Fonts.FontDefault);
        JLabel time = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("time"));
        time.setFont(Fonts.FontDefault);
        JLabel difficulty = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("difficultyLabel"));
        difficulty.setFont(Fonts.FontDefault);
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 3, 15, 0));
        infoPanel.add(name);
        infoPanel.add(time);
        infoPanel.add(difficulty);
        JList<ScoreObjectPanel> scores = new JList<>(defaultListModel);
        scores.setCellRenderer(new ScoreBoardRenderer());
        scores.setBackground(new Color(142, 143, 144, 255));
        JScrollPane scrollableList = new JScrollPane(scores);
        JPanel scoresConstrain = new JPanel(new BorderLayout());
        scoresConstrain.add(scrollableList);
        scoresConstrain.add(infoPanel, BorderLayout.PAGE_START);
        this.add(scoresConstrain);
    }

    public void createDatabase() {
        try {
            scoreBoardController.createTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addToDatabase(String name, int time, int difficulty) {
        try {
            scoreBoardController.setScoreBoard(name, difficulty, time);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setListModel() {
        defaultListModel.removeAllElements();
        ArrayList<ScoreObjectPanel> scoreObjectPanels = null;
        try {
            scoreObjectPanels = scoreBoardController.getScoreBoard();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (scoreObjectPanels != null) {
            for (ScoreObjectPanel scoreObjectPanel : scoreObjectPanels) {
                defaultListModel.addElement(scoreObjectPanel);
            }
        }
    }

    static class ScoreBoardRenderer implements ListCellRenderer<ScoreObjectPanel> {

        @Override
        public Component getListCellRendererComponent(JList<? extends ScoreObjectPanel> list, ScoreObjectPanel value, int index, boolean isSelected, boolean cellHasFocus) {
            JPanel cell = new JPanel(new GridLayout(1, 3, 15, 0));
            JLabel name = new JLabel(value.getName());
            name.setFont(Fonts.FontDefault);
            cell.add(name);
            JLabel time = new JLabel(value.getTime());
            time.setFont(Fonts.FontDefault);
            cell.add(time);
            JLabel difficulty = new JLabel(value.getDifficulty());
            difficulty.setFont(Fonts.FontDefault);
            cell.add(difficulty);

            if (isSelected) {
                cell.setBackground(list.getSelectionBackground());
                cell.setForeground(list.getSelectionForeground());
            } else {
                cell.setBackground(list.getBackground());
                cell.setForeground(list.getForeground());
            }

            return cell;
        }
    }

}
