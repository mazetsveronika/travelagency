package by.mazets.travelagency.dao;

import by.mazets.travelagency.exception.TravelAgencyDaoException;

/**
 * Interface {@code TourDao}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public interface TourDao extends EntityDao {

    void setHotTour(int id, boolean isHot) throws TravelAgencyDaoException;

}