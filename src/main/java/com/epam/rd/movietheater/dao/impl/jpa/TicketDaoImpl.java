package com.epam.rd.movietheater.dao.impl.jpa;

import com.epam.rd.movietheater.dao.TicketDao;
import com.epam.rd.movietheater.dao.impl.jpa.repository.TicketRepository;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TicketDaoImpl implements TicketDao {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketDaoImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findByEvent(Event event) {
        return ticketRepository.findByEvent(event);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public boolean remove(Ticket ticket) {
        ticketRepository.delete(ticket);
        return ticketRepository.findById(ticket.getId()).isPresent();
    }

    @Override
    public Optional<Ticket> getById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }
}
