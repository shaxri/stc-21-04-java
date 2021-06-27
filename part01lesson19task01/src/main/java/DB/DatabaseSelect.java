package DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseSelect {
    private static final Logger logger = LogManager.getLogger(DatabaseSelect.class);
    private Connection connection;

    public DatabaseSelect(Connection connection) {
        this.connection = connection;
    }

    public void outputTableUser() throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CrudOperations.SELECT_FROM_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            logger.debug("Table Users");
            while (resultSet.next()) {
                logger.debug("id=" + resultSet.getLong("id") +
                        "; login=" + resultSet.getString("login") +
                        "; password=" + resultSet.getString("password") +
                        "; name=" + resultSet.getString("name") +
                        "; email=" + resultSet.getString("email"));
            }
            logger.debug("");
        }
    }

    public void outputTableProduct() throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CrudOperations.SELECT_FROM_PRODUCTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            logger.debug("Table Products");
            while (resultSet.next()) {
                logger.debug("id=" + resultSet.getLong("id") +
                        "; name=" + resultSet.getString("name") +
                        "; price=" + resultSet.getDouble("price") +
                        "; manufacturer=" + resultSet.getString("manufacturer"));
            }
            logger.debug("");
        }
    }

    public void outputTableOrder() throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CrudOperations.SELECT_FROM_ORDERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            logger.debug("Table Orders");
            while (resultSet.next()) {
                logger.debug("id=" + resultSet.getLong("id") +
                        "; number=" + resultSet.getLong("number") +
                        "; user=" + resultSet.getString("user_id") +
                        "; product=" + resultSet.getString("product_id") +
                        "; status=" + resultSet.getString("status") +
                        "; order_date=" + resultSet.getDate("order_date") +
                        "; delivery_information=" + resultSet.getString("delivery_information"));
            }
            logger.debug("");
        }
    }
}
