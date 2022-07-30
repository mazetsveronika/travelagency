package by.mazets.travelagency.connection;

import by.mazets.travelagency.exception.TravelAgencyConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class {@code ConnectionFactory}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger();
    private static final String FILE_NAME = "properties/database.properties";
    private static final Properties properties = new Properties();
    private static final int POOL_SIZE;
    private static AtomicBoolean create = new AtomicBoolean(false);
    private static ReentrantLock lockerCreator = new ReentrantLock();
    private static ConnectionPool instance;
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> giveAwayConnections;

    static {
        try {
            ClassLoader loader = ConnectionFactory.class.getClassLoader();
            URL resource = loader.getResource(FILE_NAME);
            if(resource == null) {
                logger.log(Level.ERROR,"Resource is null! " + FILE_NAME);
                throw new IllegalArgumentException("Resource is null!");
            }
            String fileProperties = resource.getFile();
            properties.load(new FileReader(fileProperties));
        }catch (IOException e){
            logger.log(Level.FATAL,"File properties exception: " + e.getMessage());
            throw new RuntimeException("File properties exception." + e.getMessage());
        }
        POOL_SIZE = Integer.parseInt((String) properties.get("poolsize"));
    }

    private  ConnectionPool(){
        freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        giveAwayConnections = new LinkedBlockingDeque<>();
        for(int i = 0; i < POOL_SIZE; i++){
            try{
                ProxyConnection connection = new ProxyConnection(ConnectionFactory.createConnection());
                boolean isCreated = freeConnections.offer(connection);
                logger.log(Level.DEBUG,"Connection is " + isCreated);
            }catch (TravelAgencyConnectionPoolException e){
                logger.log(Level.ERROR,"Connection was not created " + e.getMessage());
            }
        }
        if(freeConnections.isEmpty()){
            logger.log(Level.FATAL,"There are not connections!");
            throw new RuntimeException("There are not connections!");
        }else if (freeConnections.size() < POOL_SIZE){
            int connectionSize = freeConnections.size();
            while (connectionSize!= POOL_SIZE){
                try {
                    ProxyConnection connection = new ProxyConnection(ConnectionFactory.createConnection());
                    freeConnections.offer(connection);
                }catch (TravelAgencyConnectionPoolException e){
                    logger.log(Level.ERROR,"Connection was not created " + e.getMessage());
                    throw new RuntimeException("Connection was not created." + e.getMessage());
                }
                connectionSize++;
            }
        }
    }

    /**
     * Returns singleton ConnectionPool object
     * @return instance
     */
    public static ConnectionPool getInstance(){
        if(!create.get()){
            try{
                lockerCreator.lock();
                if(instance == null){
                    instance = new ConnectionPool();
                    create.set(true);
                }
            }finally {
                lockerCreator.unlock();
            }
        }
        return instance;
    }

    /**
     * Get connection from connection pool
     *
     * @return connection from connection pool
     *
     * @throws TravelAgencyConnectionPoolException
     */

    public Connection getConnection() throws TravelAgencyConnectionPoolException{
        ProxyConnection connection = null;
        try{
            connection = freeConnections.take();
            giveAwayConnections.put(connection);
        }catch (InterruptedException e){
            logger.log(Level.ERROR,"The thread was interrupted!" + e.getMessage());
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Release connection. The connection returns
     * to the pool.
     *
     * @param connection the connection
     */
    public void releaseConnection(Connection connection){
        try {
            if(connection.getClass() != ProxyConnection.class){
                throw new TravelAgencyConnectionPoolException("Illegal connection!");
            }
            ProxyConnection proxyConnection = (ProxyConnection) connection;
            giveAwayConnections.remove(proxyConnection);
            freeConnections.put(proxyConnection);
        }catch (TravelAgencyConnectionPoolException | InterruptedException e){
            logger.log(Level.ERROR,e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Destroy pool and deregister drivers.
     */
    public void destroyPool(){
        for(int i = 0; i < POOL_SIZE; i++){
            try {
                freeConnections.take().reallyClose();
                logger.log(Level.INFO,"Connection closed!");
            } catch (InterruptedException e) {
                logger.log(Level.ERROR,"The thread was interrupted!" + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers(){
        logger.log(Level.DEBUG, "Deregister driver method.");
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                logger.log(Level.INFO, "Deregister driver.");
            } catch (SQLException e) {
                logger.log(Level.ERROR, "The driver was not removed");
            }
        });
    }
}