package com.epam.rd.movietheater.dao;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;

import java.util.List;

public interface TicketDao extends IdentifiableDao<Ticket> {
    List<Ticket> findByEvent(Event event);
}
