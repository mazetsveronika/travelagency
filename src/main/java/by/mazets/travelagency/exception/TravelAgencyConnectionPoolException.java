package by.mazets.travelagency.exception;

public class TravelAgencyConnectionPoolException extends Exception{

    public TravelAgencyConnectionPoolException() {
    }

    public TravelAgencyConnectionPoolException(String message) {
        super(message);
    }

    public TravelAgencyConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelAgencyConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
