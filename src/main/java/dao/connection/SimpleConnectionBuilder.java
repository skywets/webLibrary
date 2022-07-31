package dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SimpleConnectionBuilder implements ConnectionBulder{
//    @Override
//    public Connection getConnection() throws SQLException {
//        String url = "jdbc:postgresql://localhost:5432/postgres";
//        Properties properties = new Properties();
//        properties.setProperty("newuser", "postgres");
//        properties.setProperty("1234", "newuser");
//        Connection connection = DriverManager.getConnection(url, properties);
//        return connection;
//    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, "newuser", "1234");
    }
}