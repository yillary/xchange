package com.nashss.se.exchange.exceptions;

public class ItemNotFoundException extends RuntimeException{

    /**
     * Exception with no message or cause.
     */
    public ItemNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public ItemNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with a message, but no cause.
     * @param cause the original thowable resulting in this exception.
     */
    public ItemNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     * @param cause the original throwable resulting in this exeption.
     */
    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
