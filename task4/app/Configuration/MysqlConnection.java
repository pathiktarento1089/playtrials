package Configuration;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlConnection {
    private Connection connection;
    public MysqlConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/product","root","MyNewPass");
        System.out.println("Successfully connected");
    }
    public Connection getConnection() {
        return connection;
    }
}
