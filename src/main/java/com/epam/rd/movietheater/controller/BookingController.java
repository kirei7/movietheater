package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.model.Order;
import com.epam.rd.movietheater.service.facade.BookingFacade;
import com.epam.rd.movietheater.util.userprovider.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/event/{eventId}/price")
    public Order previewTicketsForSelectedSeats(@PathVariable Long eventId, @RequestBody long[] seats) {
        return bookingFacade.previewOrder(eventId, userProvider.getCurrentUser(), seats);
    }

    @PostMapping(path = "/event/{eventId}")
    public Order bookTicketsForSelectedSeats(@PathVariable Long eventId, @RequestBody long[] seats) {
        return bookingFacade.buyTickets(eventId, userProvider.getCurrentUser(), seats);
    }

}