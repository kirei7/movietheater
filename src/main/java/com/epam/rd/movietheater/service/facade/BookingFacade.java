package com.epam.rd.movietheater.service.facade;

import com.epam.rd.movietheater.model.Order;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;

import java.util.List;

public interface BookingFacade {
    /** Creates ticket entities for given event and user,
     * with their prices and discounts*/
    List<Ticket> createTickets(Long eventId, User user, long[] seats);

    Order buyTickets(Long eventId, User user, long[] seats);

    void replenishAccount(User user, Long amount);
}
