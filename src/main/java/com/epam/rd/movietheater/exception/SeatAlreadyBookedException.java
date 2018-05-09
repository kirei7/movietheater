package com.epam.rd.movietheater.exception;

import java.util.Arrays;

public class SeatAlreadyBookedException extends RuntimeException {
    public SeatAlreadyBookedException(long[] seats) {
        super(Arrays.toString(seats));
    }
}
