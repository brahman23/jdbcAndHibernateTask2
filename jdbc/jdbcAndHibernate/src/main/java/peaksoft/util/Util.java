package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public final Connection connection;
    public Util() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/java8",
                "postgres",
                "12345"
        );
    }



    public  Connection getConnection() {
        return connection;
    }
}
