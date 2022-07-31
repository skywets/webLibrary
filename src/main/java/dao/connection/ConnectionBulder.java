package dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionBulder {
    Connection getConnection() throws SQLException;
}
