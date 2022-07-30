package by.mazets.travelagency.service;

import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyServiceException;

import java.util.List;

/**
 * Interface {@code EntityService}
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */
public interface EntityService {

    void create(AbstractEntity abstractEntity) throws TravelAgencyDaoException, TravelAgencyServiceException;
    void update (AbstractEntity abstractEntity) throws TravelAgencyDaoException, TravelAgencyServiceException;
    void delete (int id) throws TravelAgencyServiceException;
    AbstractEntity findById (int id) throws TravelAgencyServiceException;
    List<AbstractEntity> findAll () throws TravelAgencyServiceException;
}
