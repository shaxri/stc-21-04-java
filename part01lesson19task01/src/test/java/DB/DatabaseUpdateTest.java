package DB;

import DTO.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class DatabaseUpdateTest {

    @Mock
    private Connection connection;
    @Mock
    private CallableStatement callableStatement;

    @Mock
    ResultSet generatedKeys;

    @Mock
    DatabaseUpdate databaseUpdate = new DatabaseUpdate(connection);

    Product product = new Product(1, "table", 34.5, "manufacturer1");

    @BeforeEach
    void setUp() throws SQLException {
        initMocks(this);
        when(connection.prepareCall(CrudOperations.UPDATE_PRODUCTS)).thenReturn(callableStatement);
        when(callableStatement.getGeneratedKeys()).thenReturn(generatedKeys);
        when(generatedKeys.next()).thenReturn(true);
        when(generatedKeys.getLong(1)).thenReturn(1l);
        databaseUpdate = new DatabaseUpdate(connection);
    }

    @Test
    void updateProductPrice() throws SQLException {
        assertEquals(databaseUpdate.updateProductPrice(Arrays.asList(1, 2, 3)), 1l, 0);
        verify(connection, times(1)).prepareCall(CrudOperations.UPDATE_PRODUCTS);
        verify(callableStatement, times(3)).setDouble(anyInt(), anyDouble());
        verify(callableStatement, times(3)).addBatch();
        verify(callableStatement, times(1)).executeBatch();
        verify(connection, times(1)).commit();
        verify(generatedKeys, times(1)).next();
        verify(generatedKeys, times(1)).getLong(1);
    }

    @Test
    void updateProductPriceSQLException() throws SQLException {
        when(connection.prepareCall(CrudOperations.UPDATE_PRODUCTS)).thenThrow(new SQLException());
        try {
            assertEquals(databaseUpdate.updateProductPrice(Arrays.asList(1, 2, 3)), 0l, 0);
        } catch (SQLException ex) {
            verify(connection, times(1)).prepareCall(CrudOperations.UPDATE_PRODUCTS);
            verify(callableStatement, times(0)).setDouble(anyInt(), anyDouble());
            verify(callableStatement, times(0)).addBatch();
            verify(callableStatement, times(0)).executeBatch();
            verify(connection, times(0)).commit();
            verify(generatedKeys, times(0)).next();
            verify(generatedKeys, times(0)).getLong(1);
            verify(connection, times(1)).rollback();
            verify(connection, times(1)).close();
            throw ex;
        }
    }
}