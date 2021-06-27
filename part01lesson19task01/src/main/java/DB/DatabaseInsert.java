package DB;

import DTO.Order;
import DTO.Product;
import DTO.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Locale;

public class DatabaseInsert {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(DatabaseInsert.class);

    public DatabaseInsert(Connection connection) {
        this.connection = connection;
    }

    public long addInTableUser(User user) throws SQLException {
        logger.info("Start addInTableUser: " + user.toString());
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CrudOperations.INSERT_INTO_USERS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();
            logger.info("Commit transaction addInTableUser");
            connection.commit();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        } catch (Exception ex) {
            logger.error("Error in addInTableUser() method", ex);
            connection.rollback();
        }
        return 0l;
    }

    public long addInTableProduct(Product product) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(CrudOperations.INSERT_INTO_PRODUCTS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getManufacturer());

            preparedStatement.executeUpdate();
            logger.info("Commit transaction addInTableProduct");
            connection.commit();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        } catch (Exception ex) {
            logger.error("Error in addInTableProduct() method", ex);
            connection.rollback();

        }
        return 0l;
    }

    public long addInTableOrder(Order order) throws SQLException {
        logger.info("Start addInTableOrder: " + order.toString());
        try (PreparedStatement preparedStatement = connection.prepareStatement(CrudOperations.INSERT_INTO_ORDERS)) {
            preparedStatement.setLong(1, order.getNumber());
            preparedStatement.setLong(2, order.getUser().getId());
            preparedStatement.setLong(3, order.getProduct().getId());
            preparedStatement.setObject(4, order.getStatus().toString().toLowerCase(Locale.ROOT), Types.OTHER);
            preparedStatement.setDate(5, order.getOrder_date());
            preparedStatement.setString(6, order.getDelivery_information());
            preparedStatement.executeUpdate();
            logger.info("Commit transaction addInTableProduct");
            connection.commit();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            connection.rollback();
        }
        return 0l;
    }
}

