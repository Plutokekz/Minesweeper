package ui.components.panels;

import javax.swing.*;

public class ScoreBoardPanel extends JPanel {

    private final JList scores;
    private final JScrollPane scrollableList;

    public ScoreBoardPanel() {
        scores = new JList();
        scrollableList = new JScrollPane(scores);
        this.add(scrollableList);
    }

    public void setListModel(ListModel listModel) {
        scores.setModel(listModel);
    }

}
