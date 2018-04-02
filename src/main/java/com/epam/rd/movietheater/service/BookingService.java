package com.epam.rd.movietheater.service;

import com.epam.rd.movietheater.entity.Event;
import com.epam.rd.movietheater.entity.Ticket;
import com.epam.rd.movietheater.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {
    BigDecimal getTicketsPrice(Event event, LocalDate dateTime, User user, Set<Long> seats);
    void bookTickets(Set<Ticket> tickets);
    List<Ticket> getPurchasedTicketsForEvent(Event event, LocalDate dateTime);
}
