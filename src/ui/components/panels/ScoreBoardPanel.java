package ui.components.panels;

import objects.assets.lang.ResourcesLoader;
import objects.data.Fonts;
import ui.components.labels.ScoreObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScoreBoardPanel extends JPanel {

    private final DefaultListModel<ScoreObject> defaultListModel;

    public ScoreBoardPanel() {
        super(new BorderLayout(3, 3));
        this.defaultListModel = new DefaultListModel<>();
        JLabel name = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("name"));
        name.setFont(Fonts.FontDefault);
        JLabel time = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("time"));
        time.setFont(Fonts.FontDefault);
        JLabel difficulty = new JLabel(ResourcesLoader.RESOURCE_BUNDLE.getString("difficultyLabel"));
        difficulty.setFont(Fonts.FontDefault);
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 3, 0, 0));
        infoPanel.add(name);
        infoPanel.add(time);
        infoPanel.add(difficulty);
        JList<ScoreObject> scores = new JList<>(defaultListModel);
        scores.setCellRenderer(new ScoreBoardRenderer());
        scores.setBackground(new Color(142, 143, 144, 255));
        JScrollPane scrollableList = new JScrollPane(scores);
        JPanel scoresConstrain = new JPanel(new BorderLayout());
        scoresConstrain.add(scrollableList);
        scoresConstrain.add(infoPanel, BorderLayout.PAGE_START);
        this.add(scoresConstrain);
    }

    public void setListModel(ArrayList<ScoreObject> scoresObjects) {
        defaultListModel.removeAllElements();
        for (ScoreObject scoreObject : scoresObjects) {
            defaultListModel.addElement(scoreObject);
        }


    }

    static class ScoreBoardRenderer implements ListCellRenderer<ScoreObject> {

        @Override
        public Component getListCellRendererComponent(JList<? extends ScoreObject> list, ScoreObject value, int index, boolean isSelected, boolean cellHasFocus) {
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
