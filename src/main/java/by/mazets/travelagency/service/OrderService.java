package by.mazets.travelagency.service;

import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.Order;
import by.mazets.travelagency.exception.TravelAgencyServiceException;

import java.util.List;

public interface OrderService  {

    void cancelOrder (Order order) throws TravelAgencyServiceException;
    List<AbstractEntity> findByUserId (int userID) throws TravelAgencyServiceException;
}