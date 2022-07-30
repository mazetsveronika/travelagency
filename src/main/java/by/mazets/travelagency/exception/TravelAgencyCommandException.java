package by.mazets.travelagency.exception;

public class TravelAgencyCommandException extends Exception{

    public TravelAgencyCommandException() {
    }

    public TravelAgencyCommandException(String message) {
        super(message);
    }

    public TravelAgencyCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelAgencyCommandException(Throwable cause) {
        super(cause);
    }
}
