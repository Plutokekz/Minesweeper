package objects.util;

import java.nio.file.Paths;
import java.sql.*;

public class JDBCUtil {

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + Paths.get(".").toAbsolutePath().normalize().toString() + "\\minesweeper.db";
        System.out.println("Connection to SQLite has been established.");
        return DriverManager.getConnection(url);
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
