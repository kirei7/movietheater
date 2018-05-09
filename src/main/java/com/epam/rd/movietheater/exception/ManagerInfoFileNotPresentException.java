package com.epam.rd.movietheater.exception;

public class ManagerInfoFileNotPresentException extends RuntimeException {
    public ManagerInfoFileNotPresentException() {
        super("Couldn't find 'bookingManager.properties' file in classpath");
    }
}
