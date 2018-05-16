package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.exception.NotEnoughMoneyException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView commonErrorHandler(Exception ex, Model model) {
        ModelAndView mav = new ModelAndView();
        ex.printStackTrace();
        String msg = ex.getMessage();
        mav.addObject("exceptionMessage", msg == null ? ex.getClass().getSimpleName() : msg);
        mav.setViewName("error");
        return mav;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NotEnoughMoneyException.class)
    @ResponseBody
    public String notEnoughMoneyException(Exception ex) {
        return ex.getMessage();
    }

}
