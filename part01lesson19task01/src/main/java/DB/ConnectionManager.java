package DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final Logger logger = LogManager.getLogger(ConnectionManager.class);
    public static Connection getConnection() {
        logger.debug("Get connection");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "postgres");
        } catch (SQLException e) {
            logger.error("Some thing went wrong in getConnection method", e);
        }
        return connection;
    }
}
