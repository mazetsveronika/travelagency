package by.mazets.travelagency.service;

import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.exception.TravelAgencyServiceException;

import java.beans.BeanInfo;
import java.math.BigDecimal;
/**
 * Interface {@code UserService}
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */
public interface UserService extends EntityService {

    User logIn(String login, String password) throws TravelAgencyServiceException;
    void setDiscount(int id, double discount) throws TravelAgencyServiceException;
    void setMoney(int id, BigDecimal money) throws TravelAgencyServiceException;

}
