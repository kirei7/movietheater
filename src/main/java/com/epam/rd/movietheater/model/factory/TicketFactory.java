package com.epam.rd.movietheater.model.factory;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;

public abstract class TicketFactory {
    public static Ticket create(Event event, User user, long seat) {
        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setUser(user);
        ticket.setSeat(seat);
        return ticket;
    }
}
