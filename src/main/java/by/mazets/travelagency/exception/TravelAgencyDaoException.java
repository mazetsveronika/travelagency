package by.mazets.travelagency.exception;

public class TravelAgencyDaoException extends Exception{

    public TravelAgencyDaoException() {
    }

    public TravelAgencyDaoException(String message) {
        super(message);
    }

    public TravelAgencyDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelAgencyDaoException(Throwable cause) {
        super(cause);
    }
}
