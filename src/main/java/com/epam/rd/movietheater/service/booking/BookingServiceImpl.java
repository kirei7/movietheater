package com.epam.rd.movietheater.service.booking;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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
    public BigDecimal getTicketsPrice(Event event, User user, List<Long> seats) {
        return seats.stream()
                .map(seat -> calculateTicketPrice(event, user, seat))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private BigDecimal calculateTicketPrice(Event event, User user, Long seat) {
        BigDecimal rawPrice = bookingHelper.getTicketPrice(event, user, seat);
        BigDecimal discountPercents = new BigDecimal(discountService.getDiscount(user, event));
        return applyDiscount(rawPrice, discountPercents);
    }
    private BigDecimal applyDiscount(BigDecimal price, BigDecimal discount) {
        BigDecimal discountValue = price
                                    .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP)
                                    .multiply(discount);
        return price.subtract(discountValue);
    }

    @Override
    public void bookTickets(List<Ticket> tickets) {
        tickets.forEach(ticket -> bookingHelper.bookTicket(ticket));
    }

    @Override
    public List<Ticket> getPurchasedTicketsForEvent(Event event) {
        return bookingHelper.getPurchasedTicketsForEvent(event);
    }
}
