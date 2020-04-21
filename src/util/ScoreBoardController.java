package util;

import ui.components.labels.ScoreObject;

import java.sql.*;
import java.util.ArrayList;

public class ScoreBoardController {

    public void setScoreBoard(String name, String difficulty, int time){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO score (name, difficulty, time) VALUES (?, ?, ?)";

            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2, difficulty);
            pstmt.setInt(3, time);

            pstmt.executeUpdate();
        }catch (Exception e) {
            System.out.println("Database Query Error - input");
            e.printStackTrace();
        }finally {
            JDBCUtil.close(pstmt);
            JDBCUtil.close(conn);
        }
    }

    public void getScoreBoard(ArrayList scoreObjects) {
        scoreObjects.clear();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM score ORDER BY time LIMIT 1000";
        try{
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                scoreObjects.add(makeScoreObject(rs));
            }
        }catch (Exception e){
            System.out.println("Database Query Error - output");
            e.printStackTrace();
        }finally {
            JDBCUtil.close(pstmt);
            JDBCUtil.close(conn);
        }
    }

    private ScoreObject makeScoreObject(ResultSet rs) throws Exception{
        ScoreObject temp = new ScoreObject();
        temp.setName(rs.getString("name"));
        temp.setDifficulty(rs.getString("difficulty"));
        temp.setTime(rs.getInt("time"));
        return temp;
    }

}
