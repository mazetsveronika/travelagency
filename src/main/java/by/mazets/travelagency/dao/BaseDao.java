package by.mazets.travelagency.dao;

import by.mazets.travelagency.connection.ConnectionPool;
import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public abstract class BaseDao<K, T extends AbstractEntity> {
  
    protected Logger logger = LogManager.getLogger();
  
    protected Connection connection;


    abstract public long add(T t) throws TravelAgencyDaoException;


    abstract public boolean update(T t) throws TravelAgencyDaoException;


    abstract public boolean delete(K k) throws TravelAgencyDaoException;

  
    abstract public List<T> findAll() throws TravelAgencyDaoException;


    abstract public Optional<T> findById(K k) throws TravelAgencyDaoException;


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void closeConnection() {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}