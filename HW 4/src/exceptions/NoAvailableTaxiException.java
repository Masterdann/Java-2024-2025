package exceptions;

public class NoAvailableTaxiException extends Exception {
    public NoAvailableTaxiException(String message) {
        super(message);
    }
}