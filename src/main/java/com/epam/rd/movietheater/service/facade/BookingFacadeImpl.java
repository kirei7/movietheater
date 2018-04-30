package com.epam.rd.movietheater.service.facade;

import com.epam.rd.movietheater.exception.EventNotFoundException;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.booking.BookingService;
import com.epam.rd.movietheater.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingFacadeImpl implements BookingFacade {

    private BookingService bookingService;
    private EventService eventService;

    @Autowired
    public BookingFacadeImpl(BookingService bookingService, EventService eventService) {
        this.bookingService = bookingService;
        this.eventService = eventService;
    }

    @Override
    public List<Ticket> createTickets(Long eventId, User user, long[] seats) {
        return bookingService.createTicketsForEvent(
                eventService.getById(eventId).orElseThrow(EventNotFoundException::new),
                user,
                seats
        );
    }

    @Override
    public List<Ticket> buyTickets(Long eventId, User user, long[] seats) {
        List<Ticket> tickets = createTickets(eventId, user, seats);
        bookingService.bookTickets(tickets);
        return tickets;
    }
}