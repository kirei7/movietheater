package com.epam.rd.movietheater.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("Event by given id not found");
    }
}
