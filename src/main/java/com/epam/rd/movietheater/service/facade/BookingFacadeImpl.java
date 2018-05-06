package com.epam.rd.movietheater.service.facade;

import com.epam.rd.movietheater.exception.EventNotFoundException;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.model.entity.UserAccount;
import com.epam.rd.movietheater.service.booking.BookingService;
import com.epam.rd.movietheater.service.event.EventService;
import com.epam.rd.movietheater.service.user.UserService;
import com.epam.rd.movietheater.service.useraccount.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookingFacadeImpl implements BookingFacade {

    private BookingService bookingService;
    private EventService eventService;
    private UserService userService;
    private UserAccountService userAccountService;

    @Autowired
    public BookingFacadeImpl(BookingService bookingService, EventService eventService, UserService userService, UserAccountService userAccountService) {
        this.bookingService = bookingService;
        this.eventService = eventService;
        this.userService = userService;
        this.userAccountService = userAccountService;
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
        conductPayment(tickets, user);
        bookingService.bookTickets(tickets);
        return tickets;
    }

    private void conductPayment(List<Ticket> tickets, User user) {
        Long totalSum = calculateTotalSum(tickets);
        withdrawFromAccount(user.getAccount(), totalSum);
    }

    private void withdrawFromAccount(UserAccount account, Long totalSum) {
        //TODO: iplement method
    }

    private Long calculateTotalSum(List<Ticket> tickets) {
        return tickets.stream().map(ticket -> {
            BigDecimal price = ticket.getPrice();
            BigDecimal discountAmount = calculateDiscountAmount(price, ticket.getDiscount().getAmount());
            return price = price.subtract(discountAmount);
        }).reduce(new BigDecimal(0), BigDecimal::add).longValue();
    }

    private BigDecimal calculateDiscountAmount(BigDecimal basePrice, int amount) {
        return basePrice.min(basePrice.divide(new BigDecimal(100)).multiply(new BigDecimal(amount)));
    }

    @Override
    public void replenishAccount(User user, Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount for replenish can't be less than or equal to zero");
        }
        UserAccount account = user.getAccount();
        Long sum = account.getAmount() + amount;
        if (sum < 0)
            throw new IllegalArgumentException("Too big amount was given: " + amount);
        account.setAmount(sum);
    }
}