package by.mazets.travelagency.dao.impl;

import by.mazets.travelagency.connection.ConnectionPool;
import by.mazets.travelagency.dao.TourDao;

import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.Tour;
import by.mazets.travelagency.exception.TravelAgencyConnectionPoolException;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyDataWrongException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code TourDaoImpl}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class TourDaoImpl implements TourDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_UPDATE_TOUR_HOT_BY_ID = "UPDATE tours SET hot_tour=? WHERE tour_id=?;";
    private static final String SQL_INSERT_TOUR = "INSERT INTO tours (tour_type, tour_price, hot_tour) VALUES (?,?,?);";
    private static final String SQL_UPDATE_TOUR ="UPDATE tours SET tour_type=?, tour_price=?, hot_tour=? WHERE tour_id=?;";
    private static final String SQL_DELETE_TOUR_BY_ID = "DELETE FROM tours WHERE tour_id = ?;";
    private static final String SQL_SELECT_TOUR_BY_ID = "SELECT tour_id, tour_type, tour_price, hot_tour FROM tours WHERE tour_id = ?;";
    private static final String SQL_SELECT_ALL_TOUR = "SELECT tour_id, tour_type, tour_price, hot_tour FROM tours;";


    @Override
    public void setHotTour(int id, boolean isHot) throws TravelAgencyDaoException {
        logger.debug("start setHotTour tour by ID");

        if (id > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_UPDATE_TOUR_HOT_BY_ID);
                ps.setBoolean(1, isHot);
                ps.setInt(2, id);
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("setHotTour tour by ID exception ", e);
                throw new TravelAgencyDaoException("setHotTour tour by ID exception", e);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        logger.error("database access error occurs", e);
                        throw new TravelAgencyDaoException("database access error occurs", e);
                    }
                }
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                logger.debug("finish setHotTour tour by ID");
            }
        }
    }

    @Override
    public void create(AbstractEntity abstractEntity) throws TravelAgencyDaoException {
        logger.debug("start tour registration");

        if (abstractEntity instanceof Tour) {
            Tour tour = (Tour) abstractEntity;
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_INSERT_TOUR);
                ps.setString(1, tour.getType());
                ps.setBigDecimal(2, tour.getPrice());
                ps.setBoolean(3, tour.isHot());
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("user tour registration exception ", e);
                throw new TravelAgencyDaoException("user tour registration exception", e);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        logger.error("database access error occurs", e);
                        throw new TravelAgencyDaoException("database access error occurs", e);
                    }
                }
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                logger.debug("finish tour registration");
            }
        } else {
            throw new TravelAgencyDaoException("abstractEntity in method parameter is not instance of tour");
        }
    }

    @Override
    public void update(AbstractEntity abstractEntity) throws TravelAgencyDaoException {
        logger.debug("start update tour by ID");

        if (abstractEntity instanceof Tour) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            Tour tour = (Tour) abstractEntity;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_UPDATE_TOUR);
                ps.setString(1, tour.getType());
                ps.setBigDecimal(2, tour.getPrice());
                ps.setBoolean(3, tour.isHot());
                ps.setInt(4, tour.getId());
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("tour update exception ", e);
                throw new TravelAgencyDaoException("tour update exception", e);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        logger.error("database access error occurs", e);
                        throw new TravelAgencyDaoException("database access error occurs", e);
                    }
                }
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                logger.debug("finish update tour by ID");
            }
        } else {
            throw new TravelAgencyDaoException("abstractEntity in method parameter is not instance of tour");
        }
    }

    @Override
    public void delete(int id) throws TravelAgencyDaoException {
        logger.debug("start delete tour by ID");

        if(id > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_DELETE_TOUR_BY_ID);
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("tour delete by ID exception ", e);
                throw new TravelAgencyDaoException("tour delete by ID exception", e);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        logger.error("database access error occurs", e);
                        throw new TravelAgencyDaoException("database access error occurs", e);
                    }
                }
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                logger.debug("finish delete tour by ID");
            }
        }
    }

    @Override
    public AbstractEntity findById(int id) throws TravelAgencyDaoException {
        logger.debug("start find tour by ID");
        Tour tour = null;

        if (id > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                ps = connection.prepareStatement(SQL_SELECT_TOUR_BY_ID);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    tour = new Tour();
                    tour.setId(rs.getInt(1));
                    tour.setType(rs.getString(2));
                    tour.setPrice(rs.getBigDecimal(3));
                    tour.setHot(rs.getBoolean(4));
                }
            } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
                logger.error("tour find by ID exception ", e);
                throw new TravelAgencyDaoException("tour find by ID exception", e);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        logger.error("database access error occurs", e);
                        throw new TravelAgencyDaoException("database access error occurs", e);
                    }
                }
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                logger.debug("finish find tour by ID");
            }
        }
        return tour;
    }

    @Override
    public List<AbstractEntity> findAll() throws TravelAgencyDaoException {
        logger.debug("start find all tours");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Tour tour = null;
        List<AbstractEntity> tours= new ArrayList<>();;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_ALL_TOUR);
            rs = ps.executeQuery();
            while (rs.next()) {
                tour = new Tour();
                tour.setId(rs.getInt(1));
                tour.setType(rs.getString(2));
                tour.setPrice(rs.getBigDecimal(3));
                tour.setHot(rs.getBoolean(4));
                tours.add(tour);
            }
        } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
            logger.error("find all tours exception ", e);
            throw new TravelAgencyDaoException("find all tours exception", e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.error("database access error occurs", e);
                    throw new TravelAgencyDaoException("database access error occurs", e);
                }
            }
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
            logger.debug("finish find all tours");
        }
        return tours;
    }
}