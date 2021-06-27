package DB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class DatabaseSelectTest {
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    ResultSet resultSet;

    @Mock
    DatabaseSelect databaseSelect;

    @BeforeEach
    void setUp() throws SQLException {
        initMocks(this);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1l);
        when(resultSet.getString(1)).thenReturn("");
        databaseSelect = new DatabaseSelect(connection);
    }

    @Test
    void outputTableUser() throws SQLException {
        when(connection.prepareStatement(CrudOperations.SELECT_FROM_USERS)).thenReturn(preparedStatement);
        databaseSelect.outputTableUser();
        verify(connection, times(1)).prepareStatement(CrudOperations.SELECT_FROM_USERS);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(2)).next();
        verify(resultSet, times(1)).getLong(anyString());
        verify(resultSet, times(4)).getString(anyString());
        verify(resultSet, times(1)).close();
    }

    @Test
    void outputTableProduct() throws SQLException {
        when(connection.prepareStatement(CrudOperations.SELECT_FROM_PRODUCTS)).thenReturn(preparedStatement);
        databaseSelect.outputTableProduct();
        verify(connection, times(1)).prepareStatement(CrudOperations.SELECT_FROM_PRODUCTS);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(2)).next();
        verify(resultSet, times(1)).getLong(anyString());
        verify(resultSet, times(2)).getString(anyString());
        verify(resultSet, times(1)).getDouble(anyString());
        verify(resultSet, times(1)).close();
    }

    @Test
    void outputTableOrder() throws SQLException {
        when(connection.prepareStatement(CrudOperations.SELECT_FROM_ORDERS)).thenReturn(preparedStatement);
        databaseSelect.outputTableOrder();
        verify(connection, times(1)).prepareStatement(CrudOperations.SELECT_FROM_ORDERS);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(2)).next();
        verify(resultSet, times(2)).getLong(anyString());
        verify(resultSet, times(4)).getString(anyString());
        verify(resultSet, times(1)).getDate(anyString());
        verify(resultSet, times(1)).close();
    }
}