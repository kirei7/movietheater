package com.epam.rd.movietheater.service.facade;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingFacadeImpl implements BookingFacade {

    private BookingService bookingService;

    @Autowired
    public BookingFacadeImpl(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public List<Ticket> createTickets(Event event, User user, long[] seats) {
        return bookingService.createTicketsForEvent(event, user, seats);
    }

    @Override
    public List<Ticket> buyTickets(Event event, User user, long[] seats) {
        List<Ticket> tickets = createTickets(event, user, seats);
        bookingService.bookTickets(tickets);
        return tickets;
    }
}