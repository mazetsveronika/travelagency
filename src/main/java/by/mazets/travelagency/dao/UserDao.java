package by.mazets.travelagency.dao;

import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.entity.type.RoleType;
import by.mazets.travelagency.exception.TravelAgencyDaoException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public abstract class UserDao extends BaseDao<Long, User> {

    abstract public boolean updateUserPassword(String password, String login) throws TravelAgencyDaoException;


    abstract public Optional<User> findUserByLogin(String login) throws TravelAgencyDaoException;

    abstract public Optional<String> findUserPassword(String login) throws TravelAgencyDaoException;


    abstract public Optional<User> findUserByEmail(String email) throws TravelAgencyDaoException;


    abstract public List<User> findUsersByFullNameStatus(User user, int startElementNumber) throws TravelAgencyDaoException;


    abstract public List<User> findUsersBySurnameStatus(User user, int startElementNumber) throws TravelAgencyDaoException;


    abstract public List<User> findUsersBySurname(User user, int startElementNumber) throws TravelAgencyDaoException;


    abstract public List<User> findUsersByFullName(User user, int startElementNumber) throws TravelAgencyDaoException;


    abstract public List<User> findUsersByStatus(User user, int startElementNumber) throws TravelAgencyDaoException;


    abstract public List<User> findUsersByRole(RoleType roleType) throws TravelAgencyDaoException;

    abstract public List<User> findUsersByRole(RoleType roleType, int startElementNumber) throws TravelAgencyDaoException;

    abstract public List<User> findUsersTeachers(int startElementNumber) throws TravelAgencyDaoException;

    abstract public List<User> findTeachersHoldingLessons(int startElementNumber) throws TravelAgencyDaoException;


    abstract public List<User> findTeachersHoldingLessonsBySurname(String surname, int startElementNumber) throws TravelAgencyDaoException;


    abstract public List<User> findTeachersHoldingLessonsByFullName(User teacher, int startElementNumber) throws TravelAgencyDaoException;


    abstract public boolean updateUserLogin(String currentLogin, String newLogin) throws TravelAgencyDaoException;


    abstract public boolean updateUserRole(String login, RoleType currentRole) throws TravelAgencyDaoException;


    abstract public boolean updatePicture(String login, InputStream pictureStream) throws TravelAgencyDaoException;

    abstract public Optional<InputStream> loadPicture(String login) throws TravelAgencyDaoException;
}