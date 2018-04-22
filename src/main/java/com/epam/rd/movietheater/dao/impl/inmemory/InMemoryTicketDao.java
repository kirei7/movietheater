package com.epam.rd.movietheater.dao.impl.inmemory;

import com.epam.rd.movietheater.dao.TicketDao;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class InMemoryTicketDao extends AbstractInMemoryDao<Ticket> implements TicketDao {
    @Override
    public List<Ticket> findByEvent(Event event) {
        return storage.values().stream().filter(t -> t.getEvent().equals(event)).collect(toList());
    }
}
