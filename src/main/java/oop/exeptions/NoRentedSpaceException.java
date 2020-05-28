package oop.exeptions;

public class NoRentedSpaceException extends Exception {

    public NoRentedSpaceException() {
        super();
    }

    public NoRentedSpaceException(String message) {
        super(message);
    }

    public NoRentedSpaceException(String message, Throwable cause) {
        super(message, cause);
    }
}
