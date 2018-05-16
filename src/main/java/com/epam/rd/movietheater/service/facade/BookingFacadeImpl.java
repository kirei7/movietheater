package com.epam.rd.movietheater.service.facade;

import com.epam.rd.movietheater.exception.EventNotFoundException;
import com.epam.rd.movietheater.model.Order;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.security.UserRole;
import com.epam.rd.movietheater.service.booking.BookingService;
import com.epam.rd.movietheater.service.event.EventService;
import com.epam.rd.movietheater.service.payment.PaymentService;
import com.epam.rd.movietheater.service.user.UserService;
import com.epam.rd.movietheater.service.useraccount.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookingFacadeImpl implements BookingFacade {

    private BookingService bookingService;
    private EventService eventService;
    private PaymentService paymentService;


    @Autowired
    public BookingFacadeImpl(BookingService bookingService, EventService eventService, UserService userService, UserAccountService userAccountService, PaymentService paymentService) {
        this.bookingService = bookingService;
        this.eventService = eventService;
        this.paymentService = paymentService;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Order previewOrder(Long eventId, User user, long[] seats) {
        List<Ticket> tickets = createTickets(eventId, user, seats);
        BigDecimal totalSum = calculateTotalSum(tickets);
        totalSum.setScale(2);
        return new Order(tickets, totalSum.toString());
    }

    private BigDecimal calculateTotalSum(List<Ticket> tickets) {
        return tickets.stream().map(ticket -> {
            BigDecimal price = ticket.getPrice();
            BigDecimal discountAmount = calculateDiscountAmount(price, ticket.getDiscount().getAmount());
            return price.subtract(discountAmount);
        }).reduce(new BigDecimal(0), BigDecimal::add);
    }

    /**
     * Creates ticket entities for given event and user,
     * with their prices and discounts
     */
    private List<Ticket> createTickets(Long eventId, User user, long[] seats) {
        return bookingService.createTicketsForEvent(
                eventService.getById(eventId).orElseThrow(EventNotFoundException::new),
                user,
                seats
        );
    }
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Order buyTickets(Long eventId, User user, long[] seats) {
        List<Ticket> tickets = createTickets(eventId, user, seats);
        BigDecimal totalSum = conductPayment(tickets, user);
        bookingService.bookTickets(tickets);
        totalSum.setScale(2);
        return new Order(tickets, totalSum.toString());
    }

    private BigDecimal conductPayment(List<Ticket> tickets, User user) {
        BigDecimal totalSum = calculateTotalSum(tickets);
        if (!user.getRoles().contains(UserRole.BOOKING_MANAGER))
            paymentService.withdrawFromAccount(user.getAccount(), totalSum);
        return totalSum;
    }



    private BigDecimal calculateDiscountAmount(BigDecimal basePrice, int amount) {
        return basePrice.min(basePrice.divide(new BigDecimal(100)).multiply(new BigDecimal(amount)));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void replenishAccount(User user, Long amount) {
        paymentService.refillAccount(user.getAccount(), amount);
    }
}