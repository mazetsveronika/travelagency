package by.mazets.travelagency.dao;

import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.exception.TravelAgencyDaoException;

import java.util.List;

/**
 * Interface {@code EntityDao}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public interface EntityDao {

    void create(AbstractEntity abstractEntity) throws TravelAgencyDaoException;
    void update (AbstractEntity abstractEntity) throws TravelAgencyDaoException;
    void delete (int id) throws TravelAgencyDaoException;
    AbstractEntity findById (int id) throws TravelAgencyDaoException;
    List<AbstractEntity> findAll () throws TravelAgencyDaoException;

}