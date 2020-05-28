package oop.exeptions;

public class RegistrationNumberFormatException extends Error {

    public RegistrationNumberFormatException() {
        super();
    }

    public RegistrationNumberFormatException(String message) {
        super(message);
    }

    public RegistrationNumberFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
