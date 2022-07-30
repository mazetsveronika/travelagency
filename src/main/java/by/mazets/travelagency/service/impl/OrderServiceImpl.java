package by.mazets.travelagency.service.impl;


import by.mazets.travelagency.dao.DaoFactory;
import by.mazets.travelagency.dao.OrderDao;
import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.Order;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.OrderService;

import java.util.List;
/**
 * Class {@code OrderServiceImpl}
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */
public class OrderServiceImpl implements OrderService {

    private DaoFactory daoFactory = DaoFactory.getInstance();
    private OrderDao orderDao = daoFactory.getOrderDao();

    @Override
    public void cancelOrder(Order order) throws TravelAgencyServiceException {
        try {
            orderDao.cancelOrder(order);
        } catch (TravelAgencyDaoException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void create(AbstractEntity abstractEntity) throws TravelAgencyServiceException {
        if (abstractEntity instanceof Order) {
            Order order = (Order) abstractEntity;
            try {
                orderDao.create(order);
            } catch (TravelAgencyDaoException  e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
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
    public AbstractEntity findById(int id) throws TravelAgencyServiceException {
        if (id > 0) {
            try {
                AbstractEntity order = orderDao.findById(id);
                return order;
            } catch (TravelAgencyDaoException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public List<AbstractEntity> findAll() throws TravelAgencyServiceException {
        try {
            List<AbstractEntity> orders = orderDao.findAll();
            return orders;
        } catch (TravelAgencyDaoException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public List<AbstractEntity> findByUserId(int userID) throws TravelAgencyServiceException {
        try {
            List<AbstractEntity> orders = orderDao.findByUserId(userID);
            return orders;
        } catch (TravelAgencyDaoException e) {
            throw new TravelAgencyServiceException(e);
        }
    }
}