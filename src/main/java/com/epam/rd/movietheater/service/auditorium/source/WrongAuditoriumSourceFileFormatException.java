package com.epam.rd.movietheater.service.auditorium.source;

public class WrongAuditoriumSourceFileFormatException extends RuntimeException {
    public WrongAuditoriumSourceFileFormatException(String cause) {
        super("Cannot parse target file: " + cause);
    }
}
