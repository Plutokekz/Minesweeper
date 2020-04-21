package util;

import java.sql.*;

public class JDBCUtil {

    public static Connection connect(){
        try{
            Class.forName("org.sqlite.JDBC");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Driver not found");
        }

        Connection conn = null;

        try {
            String url ="jdbc:sqlite:C:/CS/Project/Minesweeper/minesweeper.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void close(ResultSet resultSet){
        try{
            if(resultSet != null){
                resultSet.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement preparedStatement){
        try {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void close(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
