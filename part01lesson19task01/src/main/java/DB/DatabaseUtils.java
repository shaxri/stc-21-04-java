package DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DatabaseUtils {
    private static final Logger logger = LogManager.getLogger(DatabaseUtils.class);
    Connection connection;
    public DatabaseUtils(Connection connection) {
        this.connection = connection;
    }
    public void renewDatabase() throws SQLException {

        logger.info("Start renewDatabase: ");
        try (Statement statement = connection.createStatement()) {
            statement.execute(CrudOperations.RENEW_DATABASE);
            logger.info("Commit transaction renewDatabase");
            connection.commit();

        } catch (Exception ex) {
            logger.error("Error in renewDatabase() method" , ex);
            connection.rollback();
        }
    }

    public void doSomeTransactions() throws SQLException {

        try (Statement pstmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            logger.debug("Set savepoint1");
            Savepoint savepoint1 = connection.setSavepoint();
            logger.info("update Products set manufacturer = 'Standard Wood Plastic' where name = 'table top'");
            try (ResultSet rs = pstmt.executeQuery(CrudOperations.SELECT_FROM_PRODUCTS)) {
                while (rs.next()) {
                    if (rs.getString("name").equalsIgnoreCase("table top")) {
                        rs.updateString("manufacturer", "Standard Wood Plastic");
                        rs.updateRow();
                    }
                }
                logger.debug("Set savepoint2");
                Savepoint savepoint2 = connection.setSavepoint();
                logger.info("Delete last row from ResultSet from query: " + CrudOperations.SELECT_FROM_PRODUCTS);
                rs.last();
                rs.deleteRow();
                logger.debug("Set savepoint3");
                Savepoint savepoint3 = connection.setSavepoint();
                logger.info("Insert row to ResultSet from query: " + CrudOperations.SELECT_FROM_PRODUCTS);
                rs.moveToInsertRow();
                rs.updateString(2, "table top");
                rs.updateDouble(3, 14);
                rs.updateString(4, "Modern Wood Plastic");
                rs.insertRow();
                logger.info("Rollback insert operation");
                connection.rollback(savepoint3);
                connection.commit();
                logger.info("Commit doSomeTransactions");
            } catch (Exception ex) {
                logger.error("Error in doSomeTransactions() method" , ex);
                connection.rollback();
            }
        } catch (Exception ex) {
            logger.error("Error in doSomeTransactions() method" , ex);
            connection.rollback();
        }
    }

}
