package com.epam.rd.movietheater.aspect;

import com.epam.rd.movietheater.dao.aspect.AspectCounterDao;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class CountAspect {

    private final Log logger = LogFactory.getLog(CountAspect.class);
    private AspectCounterDao accessesByName;
    private AspectCounterDao accessedPrice;
    private AspectCounterDao bookedTickets;

    @Autowired
    public CountAspect(
            @Qualifier("nameCounter") AspectCounterDao accessesByName,
            @Qualifier("priceCounter") AspectCounterDao accessedPrice,
            @Qualifier("ticketCounter") AspectCounterDao bookedTickets) {
        this.accessesByName = accessesByName;
        this.accessedPrice = accessedPrice;
        this.bookedTickets = bookedTickets;
    }

    @AfterReturning(
            pointcut = "execution(public * com.epam.rd.movietheater.dao.EventDao.findByName(String))",
            returning = "retVal"
    )
    protected void countAccessingByName(List<Event> retVal) {
        retVal.forEach(event -> incrementCounterForEvent(event, accessesByName));
    }

    @AfterReturning(
            pointcut = "execution(public * com.epam.rd.movietheater.service.booking.BookingHelper.calculateTicketPrice(com.epam.rd.movietheater.model.entity.Ticket)) && args(ticket)"
    )
    protected void countAccessesToPrice(Ticket ticket) {
        Event target = ticket.getEvent();
        incrementCounterForEvent(target, accessedPrice);
    }
    @AfterReturning(
            "execution(public * com.epam.rd.movietheater.service.booking.BookingHelper.bookTicket(com.epam.rd.movietheater.model.entity.Ticket)) && args(ticket)"
    )
    protected void countBookedTicketsForParticularEvent(Ticket ticket) {
        Event target = ticket.getEvent();
        incrementCounterForEvent(target, bookedTickets);
    }


    public long getAccessesByName(Event event) {
        return getValue(event, accessesByName);
    }
    public long getAccessesToPrice(Event event) {
        return getValue(event, accessedPrice);
    }
    public long getNumberOfBookedTickets(Event event) {
        return getValue(event, bookedTickets);
    }

    private Long getValue(Event event, AspectCounterDao dao) {
        return dao.getValueFor(event);
    }

    private void incrementCounterForEvent(Event event, AspectCounterDao dao) {
        dao.incrementFor(event);
    }
}
