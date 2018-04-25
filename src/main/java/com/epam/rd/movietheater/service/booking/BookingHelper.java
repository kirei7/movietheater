package com.epam.rd.movietheater.service.booking;

import com.epam.rd.movietheater.dao.EventDao;
import com.epam.rd.movietheater.dao.TicketDao;
import com.epam.rd.movietheater.dao.UserDao;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.service.booking.pricemodifier.TicketPriceModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class BookingHelper {

    private List<TicketPriceModifier> priceModifiers;
    private UserDao userDao;
    private EventDao eventDao;
    private TicketDao ticketDao;

    @Autowired
    public BookingHelper(List<TicketPriceModifier> priceModifiers, UserDao userDao, EventDao eventDao, TicketDao ticketDao) {
        this.priceModifiers = priceModifiers;
        this.userDao = userDao;
        this.eventDao = eventDao;
        this.ticketDao = ticketDao;
    }

    public BigDecimal calculateTicketPrice(Ticket ticket) {
        BigDecimal price = new BigDecimal(ticket.getEvent().getBasePrice());
        for (TicketPriceModifier m : priceModifiers) {
            price = m.applyModificationTo(price, ticket.getEvent(), ticket.getSeat()).setScale(2, RoundingMode.HALF_UP);
        }
        return price;
    }

    public void bookTicket(Ticket ticket) {
        ticketDao.save(ticket);
    }

    public List<Ticket> getPurchasedTicketsForEvent(Event event) {
        return ticketDao.findByEvent(event);
    }

}
