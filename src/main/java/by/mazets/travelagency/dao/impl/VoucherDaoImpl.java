package by.mazets.travelagency.dao.impl;

import by.mazets.travelagency.connection.ConnectionPool;
import by.mazets.travelagency.dao.VoucherDao;

import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.Hotel;
import by.mazets.travelagency.entity.Tour;
import by.mazets.travelagency.entity.Voucher;
import by.mazets.travelagency.entity.type.TransportType;
import by.mazets.travelagency.exception.TravelAgencyConnectionPoolException;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyDataWrongException;
import by.mazets.travelagency.exception.TravelAgencyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Class {@code VoucherDaoImpl}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class VoucherDaoImpl implements VoucherDao {
    
    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_VOUCHER_BY_COUNTRY
            = "SELECT voucher_id, country, dateFrom, dateTo,\n" +
            "tours.tour_id, tours.tour_type, tours.tour_price, tours.hot_tour, \n" +
            "hotels.hotel_id, hotels.hotel_name, hotels.price_per_day, \n" +
            "transports.transport_type\n" +
            "FROM vouchers \n" +
            "INNER JOIN tours ON vouchers.tours_tour_id = tours.tour_id \n" +
            "INNER JOIN hotels ON vouchers.hotels_hotel_id = hotels.hotel_id\n" +
            "INNER JOIN transports ON vouchers.transports_transport_id = transports.transport_id \n" +
            "WHERE country = ?;";
    private static final String SQL_SELECT_VOUCHER_BY_TOUR_TYPE
            = "SELECT voucher_id, country, dateFrom, dateTo,\n" +
            "tours.tour_id, tours.tour_type, tours.tour_price, tours.hot_tour, \n" +
            "hotels.hotel_id, hotels.hotel_name, hotels.price_per_day, \n" +
            "transports.transport_type\n" +
            "FROM vouchers \n" +
            "INNER JOIN tours ON vouchers.tours_tour_id = tours.tour_id \n" +
            "INNER JOIN hotels ON vouchers.hotels_hotel_id = hotels.hotel_id\n" +
            "INNER JOIN transports ON vouchers.transports_transport_id = transports.transport_id \n" +
            "WHERE tours.tour_type = ?;";
    private static final String SQL_INSERT_VOUCHER
            = "INSERT INTO vouchers (country, dateFrom, dateTo, tours_tour_id, transports_transport_id, hotels_hotel_id) VALUES (?,?,?,?,?,?);";
    private static final String SQL_UPDATE_VOUCHER_BY_ID
            = "UPDATE vouchers SET country=?, dateFrom=?, dateTo=?, tours_tour_id=?, transports_transport_id=?, hotels_hotel_id=? WHERE voucher_id=?;";
    private static final String SQL_DELETE_VOUCHER_BY_ID = "DELETE FROM vouchers WHERE voucher_id=?;";
    private static final String SQL_SELECT_VOUCHER_BY_ID
            = "SELECT voucher_id, country, dateFrom, dateTo,\n" +
            "tours.tour_id, tours.tour_type, tours.tour_price, tours.hot_tour, \n" +
            "hotels.hotel_id, hotels.hotel_name, hotels.price_per_day, \n" +
            "transports.transport_type\n" +
            "FROM vouchers \n" +
            "INNER JOIN tours ON vouchers.tours_tour_id = tours.tour_id \n" +
            "INNER JOIN hotels ON vouchers.hotels_hotel_id = hotels.hotel_id\n" +
            "INNER JOIN transports ON vouchers.transports_transport_id = transports.transport_id \n" +
            "WHERE voucher_id = ?;";
    private static final String SQL_SELECT_ALL_VOUCHER
            = "SELECT voucher_id, country, dateFrom, dateTo,\n" +
            "tours.tour_id, tours.tour_type, tours.tour_price, tours.hot_tour, \n" +
            "hotels.hotel_id, hotels.hotel_name, hotels.price_per_day, \n" +
            "transports.transport_type\n" +
            "FROM vouchers \n" +
            "INNER JOIN tours ON vouchers.tours_tour_id = tours.tour_id \n" +
            "INNER JOIN hotels ON vouchers.hotels_hotel_id = hotels.hotel_id\n" +
            "INNER JOIN transports ON vouchers.transports_transport_id = transports.transport_id \n;";


    @Override
    public List<Voucher> getVouchersByCountry(String country) throws TravelAgencyDaoException {
        logger.debug("start find vouchers by country");
        List<Voucher> vouchers = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_VOUCHER_BY_COUNTRY);
            ps.setString(1, country);
            rs = ps.executeQuery();
            while (rs.next()) {

                Voucher voucher = new Voucher();
                voucher.setId(rs.getInt(1));
                voucher.setCountry(rs.getString(2));
                voucher.setDateFrom(rs.getDate(3));
                voucher.setDateTo(rs.getDate(4));

                Tour tour = new Tour();
                tour.setId(rs.getInt(5));
                tour.setType(rs.getString(6));
                tour.setPrice(rs.getBigDecimal(7));
                tour.setHot(rs.getBoolean(8));

                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt(9));
                hotel.setName(rs.getString(10));
                hotel.setPricePerDay(rs.getBigDecimal(11));

                voucher.setTransport(TransportType.valueOf(rs.getString(12)));

                voucher.setTour(tour);
                voucher.setHotel(hotel);
                vouchers.add(voucher);
            }
        } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
            logger.error("find vouchers by country exception ", e);
            throw new TravelAgencyDaoException("find vouchers by country exception", e);
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
            logger.debug("finish find vouchers by country");
        }
        return vouchers;
    }

    @Override
    public List<Voucher> getVouchersByTourType(String type) throws TravelAgencyDaoException {
        logger.debug("start find vouchers by TourType");
        List<Voucher> vouchers = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_VOUCHER_BY_TOUR_TYPE);
            ps.setString(1, type);
            rs = ps.executeQuery();
            while (rs.next()) {

                Voucher voucher = new Voucher();
                voucher.setId(rs.getInt(1));
                voucher.setCountry(rs.getString(2));
                voucher.setDateFrom(rs.getDate(3));
                voucher.setDateTo(rs.getDate(4));

                Tour tour = new Tour();
                tour.setId(rs.getInt(5));
                tour.setType(rs.getString(6));
                tour.setPrice(rs.getBigDecimal(7));
                tour.setHot(rs.getBoolean(8));

                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt(9));
                hotel.setName(rs.getString(10));
                hotel.setPricePerDay(rs.getBigDecimal(11));

                voucher.setTransport(TransportType.valueOf(rs.getString(12)));

                voucher.setTour(tour);
                voucher.setHotel(hotel);
                vouchers.add(voucher);
            }
        } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
            logger.error("find vouchers by TourType exception ", e);
            throw new TravelAgencyDaoException("find vouchers by TourType exception", e);
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
            logger.debug("finish find vouchers by TourType");
        }
        return vouchers;
    }

    @Override
    public void create(AbstractEntity abstractEntity) throws TravelAgencyDaoException {
        logger.debug("start voucher registration");

        if (abstractEntity instanceof Voucher) {
            Voucher voucher = (Voucher) abstractEntity;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_INSERT_VOUCHER);
                ps.setString(1, voucher.getCountry());
                ps.setString(2, dateFormat.format(voucher.getDateFrom()));
                ps.setString(3, dateFormat.format(voucher.getDateTo()));
                ps.setInt(4, voucher.getTour().getId());
                ps.setInt(5, voucher.getTransport().getId());
                ps.setInt(6, voucher.getHotel().getId());
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("voucher registration exception ", e);
                throw new TravelAgencyDaoException("voucher registration exception", e);
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
                logger.debug("finish voucher registration");
            }
        } else {
            throw new TravelAgencyDaoException("abstractEntity in method parameter is not instance of voucher");
        }
    }

    @Override
    public void update(AbstractEntity abstractEntity) throws TravelAgencyDaoException {
        logger.debug("start update voucher by ID");

        if (abstractEntity instanceof Voucher) {
            Voucher voucher = (Voucher) abstractEntity;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_UPDATE_VOUCHER_BY_ID);
                ps.setString(1, voucher.getCountry());
                ps.setString(2, dateFormat.format(voucher.getDateFrom()));
                ps.setString(3, dateFormat.format(voucher.getDateTo()));
                ps.setInt(4, voucher.getTour().getId());
                ps.setInt(5, voucher.getTransport().getId());
                ps.setInt(6, voucher.getHotel().getId());
                ps.setInt(7, voucher.getId());
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("voucher update exception ", e);
                throw new TravelAgencyDaoException("voucher update exception", e);
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
                logger.debug("finish update voucher by ID");
            }
        } else {
            throw new TravelAgencyDaoException("abstractEntity in method parameter is not instance of voucher");
        }
    }

    @Override
    public void delete(int id) throws TravelAgencyDaoException {
        logger.debug("start delete voucher by ID");

        if(id > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_DELETE_VOUCHER_BY_ID);
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("voucher delete by ID exception ", e);
                throw new TravelAgencyDaoException("voucher delete by ID exception", e);
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
                logger.debug("finish delete voucher by ID");
            }
        }
    }

    @Override
    public AbstractEntity findById(int id) throws TravelAgencyDaoException {
        logger.debug("start find voucher by ID: " + id);
        Voucher voucher = null;

        if (id > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                ps = connection.prepareStatement(SQL_SELECT_VOUCHER_BY_ID);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {

                    voucher = new Voucher();
                    voucher.setId(rs.getInt(1));
                    voucher.setCountry(rs.getString(2));
                    voucher.setDateFrom(rs.getDate(3));
                    voucher.setDateTo(rs.getDate(4));

                    Tour tour = new Tour();
                    tour.setId(rs.getInt(5));
                    tour.setType(rs.getString(6));
                    tour.setPrice(rs.getBigDecimal(7));
                    tour.setHot(rs.getBoolean(8));

                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getInt(9));
                    hotel.setName(rs.getString(10));
                    hotel.setPricePerDay(rs.getBigDecimal(11));

                    voucher.setTransport(TransportType.valueOf(rs.getString(12)));

                    voucher.setTour(tour);
                    voucher.setHotel(hotel);
                }
            } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
                logger.error("voucher find by ID exception ", e);
                throw new TravelAgencyDaoException("voucher find by ID exception", e);
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
                logger.debug("finish find voucher by ID: " + voucher);
            }
        }
        return voucher;
    }

    @Override
    public List<AbstractEntity> findAll() throws TravelAgencyDaoException {
        logger.debug("start find all vouchers");
        List<AbstractEntity> vouchers = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_ALL_VOUCHER);
            rs = ps.executeQuery();
            while (rs.next()) {

                Voucher voucher = new Voucher();
                voucher.setId(rs.getInt(1));
                voucher.setCountry(rs.getString(2));
                voucher.setDateFrom(rs.getDate(3));
                voucher.setDateTo(rs.getDate(4));

                Tour tour = new Tour();
                tour.setId(rs.getInt(5));
                tour.setType(rs.getString(6));
                tour.setPrice(rs.getBigDecimal(7));
                tour.setHot(rs.getBoolean(8));

                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt(9));
                hotel.setName(rs.getString(10));
                hotel.setPricePerDay(rs.getBigDecimal(11));

                voucher.setTransport(TransportType.valueOf(rs.getString(12)));

                voucher.setTour(tour);
                voucher.setHotel(hotel);
                vouchers.add(voucher);
            }
        } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
            logger.error("find all vouchers exception ", e);
            throw new TravelAgencyDaoException("find all vouchers exception", e);
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
            logger.debug("finish find all vouchers");
        }
        return vouchers;
    }
}