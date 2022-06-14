package by.mazets.travelagency.exception;

public class TravelAgencyServiceException extends Exception {

    public TravelAgencyServiceException() {
    }

    public TravelAgencyServiceException(String message) {
        super(message);
    }

    public TravelAgencyServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelAgencyServiceException(Throwable cause) {
        super(cause);
    }
}
