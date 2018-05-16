package com.epam.rd.movietheater.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException() {
        super("Not enough money to buy tickets");
    }
}
