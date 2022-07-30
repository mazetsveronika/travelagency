package by.mazets.travelagency.dao;

import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.exception.TravelAgencyDaoException;

import java.math.BigDecimal;

/**
 * Interface {@code UserDao}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public interface UserDao extends EntityDao {

    User logIn(String login, String password) throws TravelAgencyDaoException;
    void setDiscount(int id, double discount) throws TravelAgencyDaoException;
    void setMoney(int id, BigDecimal money) throws TravelAgencyDaoException;

}