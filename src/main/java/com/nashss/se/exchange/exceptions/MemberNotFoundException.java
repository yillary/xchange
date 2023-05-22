package com.nashss.se.exchange.exceptions;

public class MemberNotFoundException extends RuntimeException{

    /**
     * Exception with no message or cause.
     */
    public MemberNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public MemberNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with a message, but no cause.
     * @param cause the original thowable resulting in this exception.
     */
    public MemberNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     * @param cause the original throwable resulting in this exeption.
     */
    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
