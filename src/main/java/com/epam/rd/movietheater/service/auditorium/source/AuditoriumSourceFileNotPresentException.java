package com.epam.rd.movietheater.service.auditorium.source;

public class AuditoriumSourceFileNotPresentException extends RuntimeException {
    public AuditoriumSourceFileNotPresentException(String fileName) {
        super("File '" + fileName + "' is not present in classpath");
    }
}
