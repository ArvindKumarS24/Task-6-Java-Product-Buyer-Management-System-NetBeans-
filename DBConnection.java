import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/productdb";
        String user = "root";
        String password = ""; // Update your password if needed
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}
