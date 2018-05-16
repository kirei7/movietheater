package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.model.Order;
import com.epam.rd.movietheater.model.entity.User;
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

    @ResponseBody
    @PostMapping("/event/{eventId}/price")
    public Order previewTicketsForSelectedSeats(@PathVariable Long eventId, @RequestBody long[] seats) {
        return bookingFacade.previewOrder(eventId, userProvider.getCurrentUser(), seats);
    }

    @ResponseBody
    @PostMapping(path = "/event/{eventId}")
    public Order bookTicketsForSelectedSeats(@PathVariable Long eventId, @RequestBody long[] seats) {
        return bookingFacade.buyTickets(eventId, userProvider.getCurrentUser(), seats);
    }

    @PostMapping(path = "/refill")
    public String refillAccount(@RequestParam(name = "amount") long amount, Model model) {
        User user = userProvider.getCurrentUser();
        bookingFacade.replenishAccount(user, amount);
        model.addAttribute("user", user);
        return "user";
    }

}