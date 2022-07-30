package by.mazets.travelagency.service.impl;


import by.mazets.travelagency.dao.DaoFactory;
import by.mazets.travelagency.dao.HotelDao;

import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.HotelService;

import java.util.List;

/**
 * Class {@code HotelServiceImpl}
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */
public class HotelServiceImpl implements HotelService {

    private DaoFactory daoFactory = DaoFactory.getInstance();
    private HotelDao hotelDao = daoFactory.getHotelDao();

    public HotelServiceImpl() {
    }

    @Override
    public void create(AbstractEntity abstractEntity) throws TravelAgencyServiceException {
        if (abstractEntity != null) {
            try {
                hotelDao.create(abstractEntity);
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
                hotelDao.update(abstractEntity);
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
                hotelDao.delete(id);
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
                AbstractEntity hotel = hotelDao.findById(id);
                return hotel;
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
            List<AbstractEntity> hotels = hotelDao.findAll();
            return  hotels;
        } catch (TravelAgencyDaoException e) {
            throw new TravelAgencyServiceException(e);
        }
    }
}
