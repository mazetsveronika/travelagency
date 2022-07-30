package by.mazets.travelagency.service;


import by.mazets.travelagency.exception.TravelAgencyServiceException;

/**
 * Interface {@code TourService}
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */
public interface TourService extends EntityService {

    void setHotTour(int id, boolean isHot) throws TravelAgencyServiceException;
}
