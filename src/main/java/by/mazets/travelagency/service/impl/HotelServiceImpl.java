package by.mazets.travelagency.service.impl;
/*

import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.HotelService;

import java.util.List;

public class HotelServiceImpl implements HotelService {
    @Override
    public void create(AbstractEntity abstractEntity) throws TravelAgencyDaoException, TravelAgencyServiceException {

    }

    @Override
    public void update(AbstractEntity AbstractEntity) throws TravelAgencyDaoException, TravelAgencyServiceException {

    }

    @Override
    public void delete(int id) throws TravelAgencyServiceException {

    }

    @Override
    public AbstractEntity findById(int id) throws TravelAgencyServiceException {
        return null;
    }

    @Override
    public List<AbstractEntity> findAll() throws TravelAgencyServiceException {
        return null;
    }
/*
    private BaseDao baseDao = BaseDao.getInstance();
    private HotelDao hotelDao = baseDao.getHotelDao();

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

 */


