package by.mazets.travelagency.dao;

import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.Order;
import by.mazets.travelagency.exception.TravelAgencyDaoException;

import java.util.List;

/**
 * Interface {@code OrderDao}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public interface OrderDao extends EntityDao {

    void cancelOrder (Order order) throws TravelAgencyDaoException;
    List<AbstractEntity> findByUserId (int userId) throws TravelAgencyDaoException;

}