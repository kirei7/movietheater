package com.epam.rd.movietheater.service;

import com.epam.rd.movietheater.entity.Event;
import com.epam.rd.movietheater.entity.User;

import java.time.LocalDate;

public interface DiscountService {
    int getDiscount(User user, Event event, LocalDate dateTime, long numberOfTickets);
}
