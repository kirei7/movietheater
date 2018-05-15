package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.service.facade.BookingFacade;
import com.epam.rd.movietheater.util.userprovider.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/event/{eventId}")
    public String previewTicketsForSelectedSeats(@PathVariable Long eventId, long[] seats, Model model) {
        model.addAttribute(
                "tickets",
                bookingFacade.createTickets(eventId, userProvider.getCurrentUser(), seats)
        );
        return "preview";
    }

    @PostMapping(path = "/event/{eventId}")
    public String bookTicketsForSelectedSeats(@PathVariable Long eventId, Model model, @RequestBody long[] seats) {
        System.out.println(seats);
        model.addAttribute("order", bookingFacade.buyTickets(eventId, userProvider.getCurrentUser(), seats));
        return "bookingSuccessful";
    }

}