package com.epam.rd.movietheater.exception;

public class WrongAuditoriumSourceFileFormatException extends RuntimeException {
    public WrongAuditoriumSourceFileFormatException(String cause) {
        super("Cannot parse target file: " + cause);
    }
}
