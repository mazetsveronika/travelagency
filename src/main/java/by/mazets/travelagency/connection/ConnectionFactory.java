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

/**
 * Class {@code ConnectionFactory}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final String FILE_NAME = "properties/database.properties";
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;
    private static String fileProperties;

    static {
        try {
            ClassLoader loader = ConnectionFactory.class.getClassLoader();
            URL resource = loader.getResource(FILE_NAME);
            if(resource == null) {
                logger.log(Level.ERROR,"Resource is null! " + FILE_NAME);
                throw new IllegalArgumentException();
            }
            fileProperties = resource.getFile();
            properties.load(new FileReader(fileProperties));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        }catch (ClassNotFoundException | IOException e){
            logger.log(Level.FATAL,"File properties exception: " + fileProperties);
            throw new RuntimeException("File properties exception." + e.getMessage());
        }
        DATABASE_URL = (String) properties.get("db.url");
    }
    private  ConnectionFactory(){}

    /**
     * Create connection method Get connection using DriverManager class.
     *
     * @return the connection
     * @throws TravelAgencyConnectionPoolException the connection pool exception
     */
    static Connection createConnection() throws TravelAgencyConnectionPoolException {
        try {
            return DriverManager.getConnection(DATABASE_URL, properties);
        }catch (SQLException e){
            throw new TravelAgencyConnectionPoolException("Connection is not received: " + e.getMessage());
        }
    }
}