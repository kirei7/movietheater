package com.epam.rd.movietheater.service.booking;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface BookingService {
    List<Ticket> createTicketsForEvent(Event event, User user, long[] seats);
    void bookTickets(List<Ticket> tickets);
    List<Ticket> getPurchasedTicketsForEvent(Event event);
}
