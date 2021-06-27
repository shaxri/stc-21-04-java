package DB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class DatabaseUtilsTest {
    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    ResultSet resultSet;

    @Mock
    Savepoint savepoint1;

    @Mock
    DatabaseUtils databaseUtils;

    @BeforeEach
    void setUp(){
        initMocks(this);
        databaseUtils = new DatabaseUtils(connection);
    }

    @Test
    void renewDatabase() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
        databaseUtils.renewDatabase();
        verify(connection, times(1)).createStatement();
        verify(statement, times(1)).execute(CrudOperations.RENEW_DATABASE);
        verify(connection, times(1)).commit();
    }

    @Test
    void renewDatabaseSQLException() throws SQLException {
        when(connection.createStatement()).thenThrow(new SQLException());
        try {
            databaseUtils.renewDatabase();
        } catch (SQLException ex) {
            verify(connection, times(1)).createStatement();
            verify(statement, times(0)).execute(CrudOperations.RENEW_DATABASE);
            verify(connection, times(0)).commit();
            verify(connection, times(1)).rollback();
            verify(connection, times(1)).close();
            throw ex;
        }
    }

    @Test
    void somethingDo() throws SQLException {
        when(connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)).thenReturn(statement);
        when(connection.setSavepoint()).thenReturn(savepoint1);
        when(statement.executeQuery(CrudOperations.SELECT_FROM_PRODUCTS)).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString(anyString())).thenReturn("table");
        databaseUtils.doSomeTransactions();
        verify(connection, times(1)).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT);
        verify(statement, times(1)).executeQuery(CrudOperations.SELECT_FROM_PRODUCTS);
        verify(resultSet, times(2)).next();
        verify(resultSet, times(1)).getString(anyString());
        verify(resultSet, times(1)).updateString(anyString(), anyString());
        verify(resultSet, times(1)).updateRow();
        verify(resultSet, times(1)).last();
        verify(resultSet, times(1)).deleteRow();
        verify(resultSet, times(1)).moveToInsertRow();
        verify(resultSet, times(2)).updateString(anyInt(), anyString());
        verify(resultSet, times(1)).updateDouble(anyInt(), anyDouble());
        verify(resultSet, times(1)).insertRow();
        verify(connection, times(3)).setSavepoint();
        verify(connection, times(1)).commit();
        verify(connection, times(1)).rollback(any());
    }

    void somethingDoSQLException() throws SQLException {
        when(connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)).thenThrow(new SQLException());
        try {
            databaseUtils.doSomeTransactions();
        } catch (SQLException ex) {
            verify(connection, times(1)).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            verify(statement, times(0)).executeQuery(CrudOperations.SELECT_FROM_PRODUCTS);
            verify(resultSet, times(0)).next();
            verify(resultSet, times(0)).last();
            verify(resultSet, times(0)).deleteRow();
            verify(resultSet, times(0)).moveToInsertRow();
            verify(resultSet, times(0)).updateString(anyInt(), anyString());
            verify(resultSet, times(0)).updateDouble(anyInt(), anyDouble());
            verify(resultSet, times(0)).insertRow();
            verify(connection, times(0)).setSavepoint();
            verify(connection, times(0)).commit();
            verify(connection, times(0)).rollback(any());
            verify(connection, times(1)).rollback();
            throw ex;
        }
    }
}