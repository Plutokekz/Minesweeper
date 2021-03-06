package objects.util;

import com.sun.istack.internal.Nullable;
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

    @Deprecated()
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

    public ArrayList<ScoreObjectPanel> getScoreBoard(@Nullable int difficult) throws SQLException, ClassNotFoundException {
        connect();
        ArrayList<ScoreObjectPanel> scoreObjectPanels = new ArrayList<>();
        String sql;
        switch (difficult) {
            case 0:
                sql = "SELECT * FROM score WHERE difficulty = 0 ORDER BY time";
                break;
            case 1:
                sql = "SELECT * FROM score WHERE difficulty = 1 ORDER BY time";
                break;
            case 2:
                sql = "SELECT * FROM score WHERE difficulty = 2 ORDER BY time";
                break;
            case 3:
                sql = "SELECT * FROM score WHERE difficulty = 3 ORDER BY time";
                break;
            default:
                sql = "SELECT * FROM score ORDER BY time";
                break;
        }
        ResultSet rs = connection.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            scoreObjectPanels.add(new ScoreObjectPanel(rs.getString("name"), rs.getInt("time"), rs.getInt("difficulty")));
        }
        close();
        return scoreObjectPanels;
    }

}
