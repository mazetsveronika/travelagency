package by.mazets.travelagency.dao.impl;
import by.mazets.travelagency.connection.ConnectionPool;
import by.mazets.travelagency.dao.HotelDao;


import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.Hotel;
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
 * Class {@code HotelDaoImpl}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class HotelDaoImpl implements HotelDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_INSERT_HOTEL = "INSERT INTO hotels (hotel_name, price_per_day) VALUES (?,?);";
    private static final String SQL_UPDATE_HOTEL ="UPDATE hotels SET hotel_name=?, price_per_day=? WHERE hotel_id = ?;";
    private static final String SQL_DELETE_HOTEL_BY_ID = "DELETE FROM hotels WHERE hotel_id = ?;";
    private static final String SQL_SELECT_HOTEL_BY_ID = "SELECT hotel_id, hotel_name, price_per_day FROM hotels WHERE hotel_id = ?;";
    private static final String SQL_SELECT_ALL_HOTEL = "SELECT hotel_id, hotel_name, price_per_day FROM hotels;";


    @Override
    public void create(AbstractEntity abstractEntity) throws TravelAgencyDaoException {
        logger.debug("start hotel registration");

        if (abstractEntity instanceof Hotel) {
            Hotel hotel =(Hotel) abstractEntity;
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_INSERT_HOTEL);
                ps.setString(1, hotel.getName());
                ps.setBigDecimal(2, hotel.getPricePerDay());
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("hotel registration exception ", e);
                throw new TravelAgencyDaoException("hotel registration exception", e);
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
                logger.debug("finish hotel registration");
            }
        } else {
            throw new TravelAgencyDaoException("abstractEntity in method parameter is not instance of hotel");
        }
    }

    @Override
    public void update(AbstractEntity abstractEntity) throws TravelAgencyDaoException {
        logger.debug("start update hotel by ID");

        if (abstractEntity instanceof Hotel) {
            Hotel hotel =(Hotel) abstractEntity;
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_UPDATE_HOTEL);
                ps.setString(1, hotel.getName());
                ps.setBigDecimal(2, hotel.getPricePerDay());
                ps.setInt(3, hotel.getId());
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("hotel update exception ", e);
                throw new TravelAgencyDaoException("hotel update exception", e);
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
                logger.debug("finish update hotel by ID");
            }
        } else {
            throw new TravelAgencyDaoException("abstractEntity in method parameter is not instance of hotel");
        }

    }

    @Override
    public void delete(int id) throws TravelAgencyDaoException {
        logger.debug("start delete hotel by ID");

        if(id > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_DELETE_HOTEL_BY_ID);
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("hotel delete by ID exception ", e);
                throw new TravelAgencyDaoException("hotel delete by ID exception", e);
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
                logger.debug("finish delete hotel by ID");
            }
        }
    }

    @Override
    public AbstractEntity findById(int id) throws TravelAgencyDaoException {
        logger.debug("start find hotel by ID");
        Hotel hotel = null;

        if (id > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                ps = connection.prepareStatement(SQL_SELECT_HOTEL_BY_ID);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    hotel = new Hotel();
                    hotel.setId(rs.getInt(1));
                    hotel.setName(rs.getString(2));
                    hotel.setPricePerDay(rs.getBigDecimal(3));
                }
            } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
                logger.error("hotel find by ID exception ", e);
                throw new TravelAgencyDaoException("hotel find by ID exception", e);
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
                logger.debug("finish find hotel by ID");
            }
        }
        return hotel;
    }

    @Override
    public List<AbstractEntity> findAll() throws TravelAgencyDaoException {
        logger.debug("start find all hotels");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Hotel hotel = null;
        List<AbstractEntity> hotels= new ArrayList<>();;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_ALL_HOTEL);
            rs = ps.executeQuery();
            while (rs.next()) {
                hotel = new Hotel();
                hotel.setId(rs.getInt(1));
                hotel.setName(rs.getString(2));
                hotel.setPricePerDay(rs.getBigDecimal(3));
                hotels.add(hotel);
            }
        } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
            logger.error("find all hotels exception ", e);
            throw new TravelAgencyDaoException("find all hotels exception", e);
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
            logger.debug("finish find all hotels");
        }
        return hotels;
    }

}