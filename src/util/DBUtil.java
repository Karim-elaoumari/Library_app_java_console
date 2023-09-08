package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtil {
    private static Connection connection = null;
    public static Connection getConnection() {
        if (connection != null){
            return connection;
        } else {
            try {
                String url = "jdbc:mysql://localhost:3306/library_java";
                String user = "root";
                String password = "";
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }

}
