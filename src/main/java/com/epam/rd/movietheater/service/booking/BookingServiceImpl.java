package com.epam.rd.movietheater.service.booking;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingHelper bookingHelper;
    private DiscountService discountService;

    @Autowired
    public BookingServiceImpl(BookingHelper bookingHelper, DiscountService discountService) {
        this.bookingHelper = bookingHelper;
        this.discountService = discountService;
    }

    @Override
    public List<Ticket> createTicketsForEvent(Event event, User user, long[] seats) {
        User tempUser = new User(user);
        List<Ticket> tickets = Arrays.stream(seats)
                .mapToObj(seat -> new Ticket(event, tempUser, seat))
                .collect(toList());
        calculateAndAssignPrices(tickets);
        calculateAndAssignDiscounts(tickets);
        return tickets;
    }
    private void calculateAndAssignPrices(List<Ticket> tickets) {
        tickets.forEach(ticket -> ticket.setBasePrice(bookingHelper.calculateTicketPrice(ticket)));
    }
    private void calculateAndAssignDiscounts(List<Ticket> tickets) {
        discountService.assignDiscounts(tickets);
    }

    @Override
    public void bookTickets(List<Ticket> tickets) {
        tickets.forEach((bookingHelper::bookTicket));
    }

    @Override
    public List<Ticket> getPurchasedTicketsForEvent(Event event) {
        return bookingHelper.getPurchasedTicketsForEvent(event);
    }
}
