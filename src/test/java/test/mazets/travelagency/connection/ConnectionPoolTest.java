package test.mazets.travelagency.connection;

import by.mazets.travelagency.connection.ConnectionPool;
import by.mazets.travelagency.exception.TravelAgencyConnectionPoolException;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class ConnectionPoolTest {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection = null;

    @Test
    public void testGetInstance() {
        try {
            connection = connectionPool.getConnection();
            boolean expected = true;
            boolean actual = connection.isValid(1);
            assertEquals(actual,expected);
        } catch (TravelAgencyConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTakeConnection() {
        try {
            connection = connectionPool.getConnection();
            assertTrue(connection != null);
        } catch (TravelAgencyConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}