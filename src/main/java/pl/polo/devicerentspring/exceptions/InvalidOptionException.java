package pl.polo.devicerentspring.exceptions;

public class InvalidOptionException extends RuntimeException{

    public InvalidOptionException(String message) {
        super(message);
    }
}
