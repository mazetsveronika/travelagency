package by.mazets.travelagency.service.impl;



import by.mazets.travelagency.dao.DaoProvider;
import by.mazets.travelagency.dao.UserDao;
import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.entity.type.RoleType;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.UserService;
import by.mazets.travelagency.util.PasswordEncoder;
import by.mazets.travelagency.util.PhoneNumberFormatter;
import by.mazets.travelagency.validator.UserValidator;
import by.mazets.travelagency.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService instance = new UserServiceImpl();


    private UserServiceImpl() {
    }


    public static UserService getInstance() {
        return instance;
    }

        @Override
        public Optional<User> findUser (String login, String password) throws TravelAgencyServiceException {
            DaoProvider daoProvider = DaoProvider.getInstance();
            UserDao userDao = daoProvider.getUserDao(false);
            UserValidator validator = UserValidatorImpl.getInstance();
            try {
                if (validator.checkLogin(login) && validator.checkPassword(password)) {
                    Optional<User> user = userDao.findUserByLogin(login);
                    Optional<String> userPassword = userDao.findUserPassword(login);
                    Optional<String> encodedPassword = PasswordEncoder.encode(password);
                    if (user.isPresent() && userPassword.isPresent()
                            && encodedPassword.isPresent() && userPassword.get().equals(encodedPassword.get())) {
                        return user;
                    }
                }
            } catch (TravelAgencyDaoException e) {
                logger.error("Error has occurred while searching for user with login \"{}\": {}", login, e);
                throw new TravelAgencyServiceException("Error has occurred while searching for user with login \"" + login + "\": ", e);
            } finally {
                userDao.closeConnection();
            }
            return Optional.empty();
        }

    @Override
    public Optional<User> findUser(String surname, String name, RoleType roleType) throws TravelAgencyServiceException {
        return Optional.empty();
    }

    @Override
        public Optional<User> findUser (String login) throws TravelAgencyServiceException {
            DaoProvider daoProvider = DaoProvider.getInstance();
            UserDao userDao = daoProvider.getUserDao(false);
            try {
                Optional<User> user = userDao.findUserByLogin(login);
                if (user.isPresent()) {
                    return user;
                }
            } catch (TravelAgencyDaoException e) {
                logger.error("Error has occurred while finding user by login: " + e);
                throw new TravelAgencyServiceException("Error has occurred while finding user by login: ", e);
            } finally {
                userDao.closeConnection();
            }
            return Optional.empty();
        }


        @Override
        public Map<User, String> findUsers (RoleType roleType) throws TravelAgencyServiceException {
            DaoProvider daoProvider = DaoProvider.getInstance();
            UserDao userDao = daoProvider.getUserDao(false);
            try {
                List<User> allUsers = userDao.findUsersByRole(roleType);
                Map<User, String> users = new HashMap<>();
                for (User user : allUsers) {
                    users.put(user, PhoneNumberFormatter.format(user.getPhoneNumber()));
                }
                return users;
            } catch (TravelAgencyDaoException e) {
                logger.error("Error has occurred while finding users: " + e);
                throw new TravelAgencyServiceException("Error has occurred while finding users: ", e);
            } finally {
                userDao.closeConnection();
            }
        }

        @Override
        public Map<User, String> findUsers (RoleType roleType,int page) throws TravelAgencyServiceException {
            DaoProvider daoProvider = DaoProvider.getInstance();
            UserDao userDao = daoProvider.getUserDao(false);
            try {
                int startElementNumber = page * 15 - 15;
                List<User> allUsers = userDao.findUsersByRole(roleType, startElementNumber);
                Map<User, String> users = new HashMap<>();
                for (User user : allUsers) {
                    users.put(user, PhoneNumberFormatter.format(user.getPhoneNumber()));
                }
                return users;
            } catch (TravelAgencyDaoException e) {
                logger.error("Error has occurred while finding users: " + e);
                throw new TravelAgencyServiceException("Error has occurred while finding users: ", e);
            } finally {
                userDao.closeConnection();
            }
        }

    @Override
    public Map<User, String> findUsers(Map<String, String> userData, int page) throws TravelAgencyServiceException {
        return null;
    }


    @Override
        public boolean isLoginOccupied (String login) throws TravelAgencyServiceException {
            DaoProvider daoProvider = DaoProvider.getInstance();
            UserDao userDao = daoProvider.getUserDao(false);
            try {
                Optional<User> foundUser = userDao.findUserByLogin(login);
                return foundUser.isPresent();
            } catch (TravelAgencyDaoException e) {
                logger.error("Error has occurred while checking login availability: " + e);
                throw new TravelAgencyServiceException("Error has occurred while checking login availability: ", e);
            } finally {
                userDao.closeConnection();
            }
        }

        @Override
        public boolean isEmailOccupied (String email) throws TravelAgencyServiceException {
            DaoProvider daoProvider = DaoProvider.getInstance();
            UserDao userDao = daoProvider.getUserDao(false);
            try {
                Optional<User> foundUser = userDao.findUserByEmail(email);
                return foundUser.isPresent();
            } catch (TravelAgencyDaoException e) {
                logger.error("Error has occurred while checking email availability: " + e);
                throw new TravelAgencyServiceException("Error has occurred while checking email availability: ", e);
            } finally {
                userDao.closeConnection();
            }
        }

    @Override
    public boolean registerUser(Map<String, String> userData) throws TravelAgencyServiceException {
        return false;
    }

    @Override
    public boolean checkRoles(Map<String, RoleType> usersRoles) throws TravelAgencyServiceException {
        return false;
    }

    @Override
    public boolean updateRoles(Map<String, RoleType> usersRoles) throws TravelAgencyServiceException {
        return false;
    }

    @Override
    public boolean updateUserLogin(Map<String, String> userData) throws TravelAgencyServiceException {
        return false;
    }

    @Override
    public boolean updateUserAccountData(Map<String, String> userData) throws TravelAgencyServiceException {
        return false;
    }

    @Override
    public boolean updatePicture(String login, InputStream pictureStream) throws TravelAgencyServiceException {
        return false;
    }

    @Override
    public Optional<String> loadPicture(String login) throws TravelAgencyServiceException {
        return Optional.empty();
    }



}