package com.epam.rd.movietheater.exception;

public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException() {
        super("Failed to register user");
    }

    public UserRegistrationException(String msg) {
        super(msg);
    }
}
