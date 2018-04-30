package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.service.facade.BookingFacade;
import com.epam.rd.movietheater.util.userprovider.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private UserProvider userProvider;
    private BookingFacade bookingFacade;

    @Autowired
    public BookingController(UserProvider userProvider, BookingFacade bookingFacade) {
        this.userProvider = userProvider;
        this.bookingFacade = bookingFacade;
    }

    @RequestMapping("/event/{eventId}")
    public String previewTicketsForSelectedSeats(@PathVariable Long eventId, @RequestParam long[] seats, Model model) {
        model.addAttribute(
                "tickets",
                bookingFacade.createTickets(eventId, userProvider.getCurrentUser(), seats)
        );
        return "preview";
    }

    @RequestMapping(path = "/{eventId}", method = RequestMethod.POST)
    public List<Ticket> bookTicketsForSelectedSeats(@PathVariable Long eventId, @RequestBody long[] seats) {
        return bookingFacade.buyTickets(eventId, userProvider.getCurrentUser(), seats);
    }

}