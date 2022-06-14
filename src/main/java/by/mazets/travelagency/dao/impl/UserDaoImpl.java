package by.mazets.travelagency.dao.impl;


import by.mazets.travelagency.connection.ConnectionPool;
import by.mazets.travelagency.dao.UserDao;
import by.mazets.travelagency.dao.mapper.impl.UserMapper;

import by.mazets.travelagency.entity.User;

import by.mazets.travelagency.entity.type.RoleType;
import by.mazets.travelagency.exception.TravelAgencyDaoException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

import static by.mazets.travelagency.command.RequestAttribute.*;

public class UserDaoImpl extends UserDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_USERS_BY_LOGIN
            = "SELECT user_id, name, surname, login, password, email, money, phone_number, discount_percentage, roles_role_id, name, surname, discount_percentage, money, email, phone_number, login, password, roles_role_id FROM users WHERE login = ? ;";
    private static final String SQL_SELECT_USER_PASSWORD = "SELECT password FROM users WHERE login = ?";
    private static final String SQL_UPDATE_USER_DISCOUNT_BY_ID = "UPDATE users SET discount_percentage=? WHERE user_id=?;";
    private static final String SQL_UPDATE_USER_MONEY_BY_ID = "UPDATE users SET money=? WHERE user_id=?;";
    private static final String SQL_INSERT_USER
            = "INSERT INTO users (name, surname, discount_percentage, money, phone_number, email, login, password, roles_role_id) VALUES (?,?,?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE_USER_BY_ID
            = "UPDATE users SET name=?, surname=?, discount_percentage=?, money=?, phone_number=?, email=?, login=?, password=?, roles_role_id=? WHERE user_id=?;";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?;";
    private static final String SQL_SELECT_USER_BY_ID
            = "SELECT user_id, name, surname, discount_percentage, money, email, login, password, roles_role_id FROM users WHERE user_id = ?;";
    private static final String SQL_SELECT_ALL_USER
            = "SELECT user_id, name, surname, discount_percentage, money, email, login, password, roles_role_id FROM users";
//todo
    public UserDaoImpl() {
    }

    public UserDaoImpl(boolean isTransaction) {
        if (!isTransaction) {
            connection = ConnectionPool.getInstance().getConnection();
        }
    }

    @Override
    public boolean updateUserPassword(String password, String login) throws TravelAgencyDaoException {
        return false;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws TravelAgencyDaoException {
        List<User> users;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USERS_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                UserMapper userMapper = UserMapper.getInstance();
                users = userMapper.retrieve(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error has occurred while finding user by login: " + e);
            throw new TravelAgencyDaoException("Error has occurred while finding user by login: ", e);
        }
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public Optional<String> findUserPassword(String login) throws TravelAgencyDaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_PASSWORD)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(resultSet.getString(USER_PASSWORD));
                }
            }
        } catch (SQLException e) {
            logger.error("Error has occurred while finding user password: " + e);
            throw new TravelAgencyDaoException("Error has occurred while finding user password: ", e);
        }
        return Optional.empty();
    }
//todo
    @Override
    public Optional<User> findUserByEmail(String email) throws TravelAgencyDaoException {
        return Optional.empty();
    }

    @Override
    public List<User> findUsersByFullNameStatus(User user, int startElementNumber) throws TravelAgencyDaoException {
        return null;
    }

    @Override
    public List<User> findUsersBySurnameStatus(User user, int startElementNumber) throws TravelAgencyDaoException {
        return null;
    }

    @Override
    public List<User> findUsersBySurname(User user, int startElementNumber) throws TravelAgencyDaoException {
        return null;
    }

    @Override
    public List<User> findUsersByFullName(User user, int startElementNumber) throws TravelAgencyDaoException {
        return null;
    }

    @Override
    public List<User> findUsersByStatus(User user, int startElementNumber) throws TravelAgencyDaoException {
        return null;
    }

    @Override
    public List<User> findUsersByRole(RoleType roleType) throws TravelAgencyDaoException {
        return null;
    }

    @Override
    public List<User> findUsersByRole(RoleType roleType, int startElementNumber) throws TravelAgencyDaoException {
        return null;
    }

    @Override
    public List<User> findUsersTeachers(int startElementNumber) throws TravelAgencyDaoException {
        return null;
    }

    @Override
    public List<User> findTeachersHoldingLessons(int startElementNumber) throws TravelAgencyDaoException {
        return null;
    }

    @Override
    public List<User> findTeachersHoldingLessonsBySurname(String surname, int startElementNumber) throws TravelAgencyDaoException {
        return null;
    }

    @Override
    public List<User> findTeachersHoldingLessonsByFullName(User teacher, int startElementNumber) throws TravelAgencyDaoException {
        return null;
    }

    @Override
    public boolean updateUserLogin(String currentLogin, String newLogin) throws TravelAgencyDaoException {
        return false;
    }

    @Override
    public boolean updateUserRole(String login, RoleType currentRole) throws TravelAgencyDaoException {
        return false;
    }

    @Override
    public boolean updatePicture(String login, InputStream pictureStream) throws TravelAgencyDaoException {
        return false;
    }

    @Override
    public Optional<InputStream> loadPicture(String login) throws TravelAgencyDaoException {
        return Optional.empty();
    }

    @Override
    public long add(User user) throws TravelAgencyDaoException {
        return 0;
    }

    @Override
    public boolean update(User user) throws TravelAgencyDaoException {
        return false;
    }

    @Override
    public boolean delete(Long aLong) throws TravelAgencyDaoException {
        return false;
    }

    @Override
    public List<User> findAll() throws TravelAgencyDaoException {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) throws TravelAgencyDaoException {
        return Optional.empty();
    }
}


