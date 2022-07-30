package by.mazets.travelagency.service.impl;

import by.mazets.travelagency.dao.DaoFactory;
import by.mazets.travelagency.dao.UserDao;

import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.UserService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class {@code UserServiceImpl}
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */
public class UserServiceImpl implements UserService {

    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDao userDao = daoFactory.getUserDao();

    public UserServiceImpl() {
    }

    @Override
    public User logIn(String login, String password) throws TravelAgencyServiceException {
        if (login != null && password != null) {
            try {
                User user = userDao.logIn(login, password);
                return user;
            } catch (TravelAgencyDaoException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public void setDiscount(int id, double discount) throws TravelAgencyServiceException {
        if (id > 0 && discount >= 0) {
            try {
                userDao.setDiscount(id, discount);
            } catch (TravelAgencyDaoException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public void setMoney(int id, BigDecimal money) throws TravelAgencyServiceException {
        if (id > 0 && money.compareTo(money) >= 0) {
            try {
                userDao.setMoney(id, money);
            } catch (TravelAgencyDaoException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public void create(AbstractEntity abstractEntity) throws TravelAgencyServiceException {
        if (abstractEntity != null) {
            try {
                userDao.create(abstractEntity);
            } catch (TravelAgencyDaoException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public void update(AbstractEntity abstractEntity) throws TravelAgencyServiceException {
        if (abstractEntity != null) {
            try {
                userDao.update(abstractEntity);
            } catch (TravelAgencyDaoException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public void delete(int id) throws TravelAgencyServiceException {
        if (id > 0) {
            try {
                userDao.delete(id);
            } catch (TravelAgencyDaoException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public AbstractEntity findById(int id) throws TravelAgencyServiceException {
        if (id > 0) {
            try {
                AbstractEntity user;
                user = userDao.findById(id);
                return user;
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
            List<AbstractEntity> users = userDao.findAll();
            return users;
        } catch (TravelAgencyDaoException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

}
