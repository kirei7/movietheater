package com.epam.rd.movietheater.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public void testErrorHandler(Exception ex) {
        //return ex.getMessage() + " [modified]";
    }
}
