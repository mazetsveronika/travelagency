package by.mazets.travelagency.service;

import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.Order;
import by.mazets.travelagency.exception.TravelAgencyServiceException;

import java.util.List;
/**
 * Interface {@code OrderService}
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */

public interface OrderService extends EntityService {

    void cancelOrder (Order order) throws TravelAgencyServiceException;
    List<AbstractEntity> findByUserId (int userID) throws TravelAgencyServiceException;
}
