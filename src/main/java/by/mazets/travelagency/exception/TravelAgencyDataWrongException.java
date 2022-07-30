package by.mazets.travelagency.exception;

public class TravelAgencyDataWrongException extends Exception{
    public TravelAgencyDataWrongException() {
    }

    public TravelAgencyDataWrongException(String message) {
        super(message);
    }

    public TravelAgencyDataWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelAgencyDataWrongException(Throwable cause) {
        super(cause);
    }
}
