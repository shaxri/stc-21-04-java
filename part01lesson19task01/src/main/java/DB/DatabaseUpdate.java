package DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DatabaseUpdate {
    private static final Logger logger = LogManager.getLogger(DatabaseUpdate.class);
    Connection connection;
    public DatabaseUpdate(Connection connection) {
        this.connection = connection;
    }
    public long updateProductPrice(List<Integer> ids) throws SQLException {
        logger.info("Start updateProductPrice: ");
        try (CallableStatement callableStatement = connection.prepareCall(
                CrudOperations.UPDATE_PRODUCTS)) {
            for (Integer id : ids) {
                logger.debug("update Products set price= 20.1 where id = " + id);
                callableStatement.setDouble(1, id);
                callableStatement.addBatch();
            }
            callableStatement.executeBatch();
            logger.info("Commit transaction updateProductPrice");
            connection.commit();

            try (ResultSet generatedKeys = callableStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        } catch (Exception ex) {
            logger.error("Error in updateProductPrice() method" , ex);
            connection.rollback();
        }
        return 0l;
    }
}
