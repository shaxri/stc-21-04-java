package DB;

import DTO.Order;
import DTO.OrderStatus;
import DTO.Product;
import DTO.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
class DatabaseInsertTest {
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    ResultSet generatedKeys;

    @Mock
    DatabaseInsert databaseInsert;

    User user = User.builder()
            .id(1)
            .login("evolodenko")
            .password("qwerty")
            .name("ekaterina")
            .email("evolodenko@mail.ru")
            .build();
    Product product = new Product(1, "table", 34.5, "manufacturer1");
    Order order = new Order(1, 1, user, product, OrderStatus.CREATED, new Date(121, 3, 20),
            "Moscow");

    @BeforeEach
    void setUp() throws SQLException {
        initMocks(this);
        when(preparedStatement.getGeneratedKeys()).thenReturn(generatedKeys);
        when(generatedKeys.next()).thenReturn(true);
        when(generatedKeys.getLong(1)).thenReturn(1l);
        databaseInsert = new DatabaseInsert(connection);
    }

    @Test
    void addInTableUser() throws SQLException {
        when(connection.prepareStatement(CrudOperations.INSERT_INTO_USERS)).thenReturn(preparedStatement);
        assertEquals(databaseInsert.addInTableUser(user), 1l, 0);
        verify(connection, times(1)).prepareStatement(CrudOperations.INSERT_INTO_USERS);
        verify(preparedStatement, times(4)).setString(anyInt(), anyString());
        verify(preparedStatement, times(1)).executeUpdate();
        verify(connection, times(1)).commit();
        verify(generatedKeys, times(1)).next();
        verify(generatedKeys, times(1)).getLong(1);
    }

    @Test
    void addInTableUserSQLException() throws SQLException {
        when(connection.prepareStatement(CrudOperations.INSERT_INTO_USERS)).thenThrow(new SQLException());
        try {
            assertEquals(databaseInsert.addInTableUser(user), 0l, 0);
        } catch (SQLException ex) {
            verify(connection, times(1)).prepareStatement(CrudOperations.INSERT_INTO_USERS);
            verify(preparedStatement, times(0)).setString(anyInt(), anyString());
            verify(preparedStatement, times(0)).execute();
            verify(connection, times(0)).commit();
            verify(generatedKeys, times(0)).next();
            verify(generatedKeys, times(0)).getLong(1);
            verify(connection, times(1)).rollback();
            verify(connection, times(1)).close();
            throw ex;
        }
    }

    @Test
    void addInTableProduct() throws SQLException {
        when(connection.prepareStatement(CrudOperations.INSERT_INTO_PRODUCTS)).thenReturn(preparedStatement);
        assertEquals(databaseInsert.addInTableProduct(product), 1l, 0);
        verify(connection, times(1)).prepareStatement(CrudOperations.INSERT_INTO_PRODUCTS);
        verify(preparedStatement, times(1)).setDouble(anyInt(), anyDouble());
        verify(preparedStatement, times(2)).setString(anyInt(), anyString());
        verify(preparedStatement, times(1)).executeUpdate();
        verify(connection, times(1)).commit();
        verify(generatedKeys, times(1)).next();
        verify(generatedKeys, times(1)).getLong(1);
    }

    @Test
    void addInTableProductSQLException() throws SQLException {
        when(connection.prepareStatement(CrudOperations.INSERT_INTO_PRODUCTS)).thenThrow(new SQLException());
        try {
            assertEquals(databaseInsert.addInTableProduct(product), 0l, 0);
        } catch (SQLException ex) {
            verify(connection, times(1)).prepareStatement(CrudOperations.INSERT_INTO_PRODUCTS);
            verify(preparedStatement, times(0)).setDouble(anyInt(), anyDouble());
            verify(preparedStatement, times(0)).setString(anyInt(), anyString());
            verify(preparedStatement, times(0)).execute();
            verify(connection, times(0)).commit();
            verify(generatedKeys, times(0)).next();
            verify(generatedKeys, times(0)).getLong(1);
            verify(connection, times(1)).rollback();
            verify(connection, times(1)).close();
            throw ex;
        }
    }

    @Test
    void addInTableOrder() throws SQLException {
        when(connection.prepareStatement(CrudOperations.INSERT_INTO_ORDERS)).thenReturn(preparedStatement);
        assertEquals(databaseInsert.addInTableOrder(order), 1l, 0);
        verify(connection, times(1)).prepareStatement(CrudOperations.INSERT_INTO_ORDERS);
        verify(preparedStatement, times(3)).setLong(anyInt(), anyLong());
        verify(preparedStatement, times(1)).setObject(anyInt(), anyString(), anyInt());
        verify(preparedStatement, times(1)).setDate(anyInt(), any());
        verify(preparedStatement, times(1)).setString(anyInt(), anyString());
        verify(preparedStatement, times(1)).executeUpdate();
        verify(connection, times(1)).commit();
        verify(generatedKeys, times(1)).next();
        verify(generatedKeys, times(1)).getLong(1);
    }

    @Test
    void addInTableOrderSQLException() throws SQLException {
        when(connection.prepareStatement(CrudOperations.INSERT_INTO_ORDERS)).thenThrow(new SQLException());
        try {
            assertEquals(databaseInsert.addInTableOrder(order), 0l, 0);
        } catch (SQLException ex) {
            verify(connection, times(1)).prepareStatement(CrudOperations.INSERT_INTO_ORDERS, anyInt());
            verify(preparedStatement, times(0)).setLong(anyInt(), anyLong());
            verify(preparedStatement, times(0)).setObject(anyInt(), anyString(), anyInt());
            verify(preparedStatement, times(0)).setDate(anyInt(), any());
            verify(preparedStatement, times(0)).setString(anyInt(), anyString());
            verify(preparedStatement, times(0)).execute();
            verify(connection, times(0)).commit();
            verify(generatedKeys, times(0)).next();
            verify(generatedKeys, times(0)).getLong(1);
            verify(connection, times(1)).rollback();
            verify(connection, times(1)).close();
            throw ex;
        }
    }


}