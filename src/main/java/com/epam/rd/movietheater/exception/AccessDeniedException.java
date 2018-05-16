package com.epam.rd.movietheater.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String msg) {
        super(msg);
    }
}
