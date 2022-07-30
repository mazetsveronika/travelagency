package by.mazets.travelagency.dao.impl;

import by.mazets.travelagency.connection.ConnectionPool;
import by.mazets.travelagency.dao.UserDao;

import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.entity.type.RoleType;
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
 * Class {@code UserDaoImpl}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD
            = "SELECT user_id, name, surname, discount_percentage, money, email, login, password, roles_role_id FROM users WHERE login = ? AND password=?;";
    private static final String SQL_UPDATE_USER_DISCOUNT_BY_ID = "UPDATE users SET discount_percentage=? WHERE user_id=?;";
    private static final String SQL_UPDATE_USER_MONEY_BY_ID = "UPDATE users SET money=? WHERE user_id=?;";
    private static final String SQL_INSERT_USER
            = "INSERT INTO users (name, surname, discount_percentage, money, email, login, password, roles_role_id) VALUES (?,?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE_USER_BY_ID
            = "UPDATE users SET name=?, surname=?, discount_percentage=?, money=?, email=?, login=?, password=?, roles_role_id=? WHERE user_id=?;";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?;";
    private static final String SQL_SELECT_USER_BY_ID
            = "SELECT user_id, name, surname, discount_percentage, money, email, login, password, roles_role_id FROM users WHERE user_id = ?;";
    private static final String SQL_SELECT_ALL_USER
            = "SELECT user_id, name, surname, discount_percentage, money, email, login, password, roles_role_id FROM users";

    @Override
    public User logIn(String login, String password) throws TravelAgencyDaoException {
        logger.debug("start user logIn");
        User user = null;

        if (login !=null && login != "" && password !=null && password != "") {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                connection = connectionPool.getConnection();
                ps = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_AND_PASSWORD);
                ps.setString(1, login);
                ps.setString(2, password);
                rs = ps.executeQuery();

                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt(1));
                    user.setName(rs.getString(2));
                    user.setSurname(rs.getString(3));
                    user.setDiscount(rs.getDouble(4));
                    user.setMoney(rs.getBigDecimal(5));
                    user.setEmail(rs.getString(6));
                    user.setLogin(rs.getString(7));
                    user.setPassword(rs.getString(8));
                    user.setRole(RoleType.getValue(rs.getInt(9)));
                }
            } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
                logger.error("user logIn exception ", e);
                throw new TravelAgencyDaoException("user logIn exception ", e);
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
                logger.debug("finish user registration");
            }
        }
        return user;
    }

    @Override
    public void setDiscount(int id, double discount) throws TravelAgencyDaoException {
        logger.debug("start setDiscount user by ID");

        if (id > 0 && discount >= 0 && discount <= 100) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_UPDATE_USER_DISCOUNT_BY_ID);
                ps.setDouble(1, discount);
                ps.setInt(2, id);
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("user setDiscount exception ", e);
                throw new TravelAgencyDaoException("user setDiscount exception ", e);
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
                logger.debug("finish setDiscount user by ID");
            }
        }
    }

    @Override
    public void setMoney(int id, BigDecimal money) throws TravelAgencyDaoException {
        logger.debug("start setMoney user by ID");

        if (id > 0 && money.compareTo(money) >= 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_UPDATE_USER_MONEY_BY_ID);
                ps.setBigDecimal(1, money);
                ps.setInt(2, id);
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException e) {
                logger.error("user setMoney exception ConnectionPool ", e);
                throw new TravelAgencyDaoException("user setMoney exception ", e.getCause());
            } catch (SQLException e) {
                logger.error("user setMoney exception SQLException ", e);
                throw new TravelAgencyDaoException("user setMoney exception ", e.getCause());
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
                logger.debug("finish setMoney user by ID");
            }
        }
    }

    /**
     * Add user to the database
     *
     * @param abstractEntity
     * @throws TravelAgencyDaoException
     */
    @Override
    public void create(AbstractEntity abstractEntity) throws TravelAgencyDaoException {
        logger.debug("start user registration");

        if (abstractEntity instanceof User) {
            User user = (User) abstractEntity;
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_INSERT_USER);
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.setDouble(3, user.getDiscount());
                ps.setBigDecimal(4, user.getMoney());
                ps.setString(5, user.getEmail());
                ps.setString(6, user.getLogin());
                ps.setString(7, user.getPassword());
                ps.setInt(8, user.getRole().getId());
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("user registration exception ", e);
                throw new TravelAgencyDaoException("user registration exception ", e);
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
                logger.debug("finish user registration");
            }
        } else {
            throw new TravelAgencyDaoException("abstractEntity in method parameter is not instance of user");
        }
    }

    @Override
    public void update(AbstractEntity abstractEntity) throws TravelAgencyDaoException {
        logger.debug("start update user by ID");

        if (abstractEntity instanceof User) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            User user = (User) abstractEntity;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_UPDATE_USER_BY_ID);
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.setDouble(3, user.getDiscount());
                ps.setBigDecimal(4, user.getMoney());
                ps.setString(5, user.getEmail());
                ps.setString(6, user.getLogin());
                ps.setString(7, user.getPassword());
                ps.setInt(8, user.getRole().getId());
                ps.setInt(9, user.getId());
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("user update exception ", e);
                throw new TravelAgencyDaoException("user update exception ", e);
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
                logger.debug("finish update user by ID");
            }
        } else {
            throw new TravelAgencyDaoException("abstractEntity in method parameter is not instance of user");
        }
    }

    @Override
    public void delete(int id) throws TravelAgencyDaoException {
        logger.debug("start delete user by ID");

        if(id > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                logger.error("user delete by ID exception ", e);
                throw new TravelAgencyDaoException("user delete by ID exception ", e);
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
                logger.debug("finish delete user by ID");
            }
        }
    }

    @Override
    public AbstractEntity findById(int id) throws TravelAgencyDaoException {
        logger.debug("start find user by ID");
        User user = null;

        if (id > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.getConnection();
                ps = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt(1));
                    user.setName(rs.getString(2));
                    user.setSurname(rs.getString(3));
                    user.setDiscount(rs.getDouble(4));
                    user.setMoney(rs.getBigDecimal(5));
                    user.setEmail(rs.getString(6));
                    user.setLogin(rs.getString(7));
                    user.setPassword(rs.getString(8));
                    user.setRole(RoleType.getValue(rs.getInt(9)));
                }
            } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
                logger.error("user find by ID exception ", e);
                throw new TravelAgencyDaoException("user find by ID exception ", e);
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
                logger.debug("finish find user by ID");
            }
        }
        return user;
    }

    @Override
    public List<AbstractEntity> findAll() throws TravelAgencyDaoException {
        logger.debug("start find all users");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        User user = null;
        List<AbstractEntity> users= new ArrayList<>();;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_ALL_USER);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setSurname(rs.getString(3));
                user.setDiscount(rs.getDouble(4));
                user.setMoney(rs.getBigDecimal(5));
                user.setEmail(rs.getString(6));
                user.setLogin(rs.getString(7));
                user.setPassword(rs.getString(8));
                user.setRole(RoleType.getValue(rs.getInt(9)));
                users.add(user);
            }
        } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
            logger.error("find all users exception ", e);
            throw new TravelAgencyDaoException("find all users exception ", e);
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
            logger.debug("finish find all users");
        }
        return users;
    }

}