package by.mazets.travelagency.dao.impl;

import by.mazets.travelagency.connection.ConnectionPool;
import by.mazets.travelagency.dao.OrderDao;


import by.mazets.travelagency.entity.*;
import by.mazets.travelagency.entity.type.RoleType;
import by.mazets.travelagency.entity.type.TransportType;
import by.mazets.travelagency.exception.TravelAgencyConnectionPoolException;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyDataWrongException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code OrderDaoImpl}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class OrderDaoImpl implements OrderDao {
    
    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_INSERT_ORDER = "INSERT INTO orders (vouchers_voucher_id, users_user_id, total_price) VALUES (?,?,?)";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE order_id = ?";//??;
    private static final String SQL_SELECT_ORDER_BY_ID
            = "SELECT order_id, voucher_id, country, dateFrom, dateTo,\n" +
            "tours.tour_id, tours.tour_type, tours.tour_price, tours.hot_tour, \n" +
            "hotels.hotel_id, hotels.hotel_name, hotels.price_per_day, \n" +
            "transports.transport_type,\n" +
            "users.user_id, users.name, users.surname, \n" +
            "users.discount_percentage, users.money, users.email, users.login, users.password, users.roles_role_id,\n" +
            "orders.total_price\n" +
            "FROM orders \n" +
            "INNER JOIN vouchers ON orders.vouchers_voucher_id = vouchers.voucher_id\n" +
            "INNER JOIN users ON orders.users_user_id = users.user_id\n" +
            "INNER JOIN tours ON vouchers.tours_tour_id = tours.tour_id \n" +
            "INNER JOIN hotels ON vouchers.hotels_hotel_id = hotels.hotel_id\n" +
            "INNER JOIN transports ON vouchers.transports_transport_id = transports.transport_id \n" +
            "WHERE order_id = ?;";

    private static final String SQL_SELECT_ORDER_BY_USER_ID
            = "SELECT order_id, voucher_id, country, dateFrom, dateTo,\n" +
            "tours.tour_id, tours.tour_type, tours.tour_price, tours.hot_tour,\n" +
            "hotels.hotel_id, hotels.hotel_name, hotels.price_per_day, \n" +
            "transports.transport_type,\n" +
            "users.user_id, users.name, users.surname, \n" +
            "users.discount_percentage, users.money, users.email, users.login, users.password, users.roles_role_id,\n" +
            "orders.total_price\n" +
            "FROM orders \n" +
            "INNER JOIN vouchers ON orders.vouchers_voucher_id = vouchers.voucher_id\n" +
            "INNER JOIN users ON orders.users_user_id = users.user_id\n" +
            "INNER JOIN tours ON vouchers.tours_tour_id = tours.tour_id \n" +
            "INNER JOIN hotels ON vouchers.hotels_hotel_id = hotels.hotel_id\n" +
            "INNER JOIN transports ON vouchers.transports_transport_id = transports.transport_id \n" +
            "WHERE users_user_id = ?;";

    private static final String SQL_SELECT_ALL_ORDER
            = "SELECT order_id, voucher_id, country, dateFrom, dateTo,\n" +
            "tours.tour_id, tours.tour_type, tours.tour_price, tours.hot_tour,\n" +
            "hotels.hotel_id, hotels.hotel_name, hotels.price_per_day, \n" +
            "transports.transport_type,\n" +
            "users.user_id, users.name, users.surname, \n" +
            "users.discount_percentage, users.money, users.email, users.login, users.password, users.roles_role_id,\n" +
            "orders.total_price\n" +
            "FROM orders \n" +
            "INNER JOIN vouchers ON orders.vouchers_voucher_id = vouchers.voucher_id\n" +
            "INNER JOIN users ON orders.users_user_id = users.user_id\n" +
            "INNER JOIN tours ON vouchers.tours_tour_id = tours.tour_id \n" +
            "INNER JOIN hotels ON vouchers.hotels_hotel_id = hotels.hotel_id\n" +
            "INNER JOIN transports ON vouchers.transports_transport_id = transports.transport_id ;";

    private static final String SQL_UPDATE_USER_MONEY_BY_ID = "UPDATE users SET money=? WHERE user_id=?;";



    @Override
    public void create(AbstractEntity abstractEntity) throws TravelAgencyDaoException {
        logger.debug("start order registration");

        if (abstractEntity instanceof Order) {
            Order order = (Order) abstractEntity;
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            PreparedStatement psUser = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_INSERT_ORDER);
                ps.setInt(1, order.getVoucher().getId());
                ps.setInt(2, order.getUser().getId());
              //  order.setTotalPrice(calculateTotalPrice(order));
                ps.setBigDecimal(3, order.getTotalPrice());
                ps.executeUpdate();

                psUser = connection.prepareStatement(SQL_UPDATE_USER_MONEY_BY_ID);
                psUser.setBigDecimal(1, (order.getUser().getMoney()).subtract(order.getTotalPrice()));//fixme
                psUser.setInt(2, order.getUser().getId());
                psUser.executeUpdate();

                connection.commit();

            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("order registration exception ", e);
                throw new TravelAgencyDaoException("order registration exception", e);
            } finally {
                if (ps != null || psUser != null) {
                    try {
                        ps.close();
                        psUser.close();
                    } catch (SQLException e) {
                        logger.error("database access error occurs", e);
                        throw new TravelAgencyDaoException("database access error occurs", e);
                    }
                }
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                logger.debug("finish order registration");
            }
        } else {
            throw new TravelAgencyDaoException("abstractEntity in method parameter is not instance of order");
        }
    }


    /**
     * Returns order total price. It depends on tour price and hotel price per day for the entire stay.
     *
     * @param order
     * @return
     */

    private BigDecimal calculateTotalPrice(Order order) {
        int nights = (int)(order.getVoucher().getDateTo().getTime() - order.getVoucher().getDateFrom().getTime())/(24 * 60 * 60 * 1000);
        BigDecimal totalPrice = (order.getVoucher().getHotel().getPricePerDay().add(order.getVoucher().getTour().getPrice())).
                multiply(BigDecimal.valueOf(nights)).
                multiply(BigDecimal.valueOf((100 - order.getUser().getDiscount())/100));
        return totalPrice;
    }



    @Override
    public void update(AbstractEntity abstractEntity) {
        throw new UnsupportedOperationException("Not implemented method");
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not implemented method");
    }

    @Override
    public void cancelOrder(Order order) throws TravelAgencyDaoException {
        logger.debug("start delete order by ID");

        if(order != null) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            PreparedStatement psUser = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID);
                ps.setInt(1, order.getId());
                ps.executeUpdate();

                psUser = connection.prepareStatement(SQL_UPDATE_USER_MONEY_BY_ID);
                psUser.setBigDecimal(1, (order.getUser().getMoney()).add(order.getTotalPrice()));//fixme
                psUser.setInt(2, order.getUser().getId());
                psUser.executeUpdate();

                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("order delete by ID exception ", e);
                throw new TravelAgencyDaoException("order delete by ID exception", e);
            } finally {
                if (ps != null || psUser != null) {
                    try {
                        ps.close();
                        psUser.close();
                    } catch (SQLException e) {
                        logger.error("database access error occurs", e);
                        throw new TravelAgencyDaoException("database access error occurs", e);
                    }
                }
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                logger.debug("finish delete order by ID");
            }
        }
    }

    @Override
    public AbstractEntity findById(int id) throws TravelAgencyDaoException {
        logger.debug("start find order by ID");
        Order order = null;

        if (id > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                ps = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    order = new Order();
                    order.setId(rs.getInt(1));
                    Voucher voucher = new Voucher();
                    voucher.setId(rs.getInt(2));
                    voucher.setCountry(rs.getString(3));
                    voucher.setDateFrom(rs.getDate(4));
                    voucher.setDateTo(rs.getDate(5));

                    Tour tour = new Tour();
                    tour.setId(rs.getInt(6));
                    tour.setType(rs.getString(7));
                    tour.setPrice(rs.getBigDecimal(8));
                    tour.setHot(rs.getBoolean(9));

                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getInt(10));
                    hotel.setName(rs.getString(11));
                    hotel.setPricePerDay(rs.getBigDecimal(12));

                    voucher.setTransport(TransportType.valueOf(rs.getString(13)));

                    voucher.setTour(tour);
                    voucher.setHotel(hotel);

                    User user = new User();
                    user.setId(rs.getInt(14));
                    user.setName(rs.getString(15));
                    user.setSurname(rs.getString(16));
                    user.setDiscount(rs.getDouble(17));
                    user.setMoney(rs.getBigDecimal(18));
                    user.setEmail(rs.getString(19));
                    user.setLogin(rs.getString(20));
                    user.setPassword(rs.getString(21));
                    user.setRole(RoleType.getValue(rs.getInt(22)));

                    order.setVoucher(voucher);
                    order.setUser(user);
                    order.setTotalPrice(rs.getBigDecimal(23));
                }
            } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
                logger.error("order find by ID exception ", e);
                throw new TravelAgencyDaoException("order find by ID exception", e);
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
                logger.debug("finish find order by ID");
            }
        }
        return order;
    }

    @Override
    public List<AbstractEntity> findAll() throws TravelAgencyDaoException {
        logger.debug("start find all vouchers");
        List<AbstractEntity> orders = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_ALL_ORDER);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt(1));

                Voucher voucher = new Voucher();
                voucher.setId(rs.getInt(2));
                voucher.setCountry(rs.getString(3));
                voucher.setDateFrom(rs.getDate(4));
                voucher.setDateTo(rs.getDate(5));

                Tour tour = new Tour();
                tour.setId(rs.getInt(6));
                tour.setType(rs.getString(7));
                tour.setPrice(rs.getBigDecimal(8));
                tour.setHot(rs.getBoolean(9));

                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt(10));
                hotel.setName(rs.getString(11));
                hotel.setPricePerDay(rs.getBigDecimal(12));

                voucher.setTransport(TransportType.valueOf(rs.getString(13)));

                voucher.setTour(tour);
                voucher.setHotel(hotel);

                User user = new User();
                user.setId(rs.getInt(14));
                user.setName(rs.getString(15));
                user.setSurname(rs.getString(16));
                user.setDiscount(rs.getDouble(17));
                user.setMoney(rs.getBigDecimal(18));
                user.setEmail(rs.getString(19));
                user.setLogin(rs.getString(20));
                user.setPassword(rs.getString(21));
                user.setRole(RoleType.getValue(rs.getInt(22)));

                order.setVoucher(voucher);
                order.setUser(user);
                order.setTotalPrice(rs.getBigDecimal(23));

                orders.add(order);
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
        return orders;
    }

    @Override
    public List<AbstractEntity> findByUserId(int userId) throws TravelAgencyDaoException {
        logger.debug("start find order by userId");
        Order order = null;
        List<AbstractEntity> ordersByUserId = new ArrayList<>();

        if (userId > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                ps = connection.prepareStatement(SQL_SELECT_ORDER_BY_USER_ID);
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    order = new Order();
                    order.setId(rs.getInt(1));

                    Voucher voucher = new Voucher();
                    voucher.setId(rs.getInt(2));
                    voucher.setCountry(rs.getString(3));
                    voucher.setDateFrom(rs.getDate(4));
                    voucher.setDateTo(rs.getDate(5));

                    Tour tour = new Tour();
                    tour.setId(rs.getInt(6));
                    tour.setType(rs.getString(7));
                    tour.setPrice(rs.getBigDecimal(8));
                    tour.setHot(rs.getBoolean(9));

                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getInt(10));
                    hotel.setName(rs.getString(11));
                    hotel.setPricePerDay(rs.getBigDecimal(12));

                    voucher.setTransport(TransportType.valueOf(rs.getString(13)));

                    voucher.setTour(tour);
                    voucher.setHotel(hotel);

                    User user = new User();
                    user.setId(rs.getInt(14));
                    user.setName(rs.getString(15));
                    user.setSurname(rs.getString(16));
                    user.setDiscount(rs.getDouble(17));
                    user.setMoney(rs.getBigDecimal(18));
                    user.setEmail(rs.getString(19));
                    user.setLogin(rs.getString(20));
                    user.setPassword(rs.getString(21));
                    user.setRole(RoleType.getValue(rs.getInt(22)));

                    order.setVoucher(voucher);
                    order.setUser(user);
                    order.setTotalPrice(rs.getBigDecimal(23));

                    ordersByUserId.add(order);
                }
            } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
                logger.error("order find by userId exception ", e);
                throw new TravelAgencyDaoException("order find by userId exception", e);
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
                logger.debug("finish find order by userId");
            }
        }
        return ordersByUserId;
    }

}