package by.mazets.travelagency.service;

import by.mazets.travelagency.exception.TravelAgencyServiceException;

public interface TourService {

    void setHotTour(int id, boolean isHot) throws TravelAgencyServiceException;
}