package objects.util;

import ui.components.panels.ScoreObjectPanel;

import java.sql.*;
import java.util.ArrayList;

public class ScoreBoardController {

    private final JDBCUtil jdbcUtil;
    private Connection connection;

    public ScoreBoardController() {
        jdbcUtil = new JDBCUtil();
    }

    private void connect() throws SQLException, ClassNotFoundException {
        connection = jdbcUtil.connect();
    }

    private void close() throws SQLException {
        connection.close();
    }

    public void createTable() throws SQLException, ClassNotFoundException {
        connect();
        String sql = "CREATE TABLE IF NOT EXISTS score (id integer PRIMARY KEY, name String, time Integer, difficulty Integer);";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        close();
    }

    public void setScoreBoard(String name, int difficulty, int time) throws SQLException, ClassNotFoundException {
        connect();
        String sql = "INSERT INTO score (name, difficulty, time) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, difficulty);
        preparedStatement.setInt(3, time);
        preparedStatement.execute();
        close();
    }

    public ArrayList<ScoreObjectPanel> getScoreBoard() throws SQLException, ClassNotFoundException {
        connect();
        ArrayList<ScoreObjectPanel> scoreObjectPanels = new ArrayList<>();
        String sql = "SELECT * FROM score ORDER BY time";
        ResultSet rs = connection.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            scoreObjectPanels.add(new ScoreObjectPanel(rs.getString("name"), rs.getInt("time"), rs.getInt("difficulty")));
        }
        close();
        return scoreObjectPanels;
    }

}
