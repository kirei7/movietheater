package com.epam.rd.movietheater.exception;

public class IllegalFileFormatException extends RuntimeException {
    public IllegalFileFormatException(String msg) {
        super(msg);
    }

    public IllegalFileFormatException(String formatGiven, String formatExpected) {
        super("Illegal file format: '" + formatGiven + "', expected: '" + formatExpected + "'");
    }
}
