package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.controller.userprovider.UserProvider;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.service.facade.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private UserProvider userProvider;
    private BookingFacade bookingFacade;

    @Autowired
    public BookingController(UserProvider userProvider, BookingFacade bookingFacade) {
        this.userProvider = userProvider;
        this.bookingFacade = bookingFacade;
    }

    @RequestMapping("/{eventId}")
    public List<Ticket> previewTicketsForSelectedSeats(@PathVariable Long eventId, @RequestParam long[] seats) {
        return bookingFacade.createTickets(eventId, userProvider.getCurrentUser(), seats);
    }

    @RequestMapping(path = "/{eventId}", method = RequestMethod.POST)
    public List<Ticket> bookTicketsForSelectedSeats(@PathVariable Long eventId, @RequestBody long[] seats) {
        return bookingFacade.buyTickets(eventId, userProvider.getCurrentUser(), seats);
    }

}
