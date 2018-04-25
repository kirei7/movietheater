package com.epam.rd.movietheater.aspect;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Aspect
@Component
public class CountAspect {

    private final Log logger = LogFactory.getLog(CountAspect.class);
    private Map<Event, AtomicLong> accessesByName = new HashMap<>();
    private Map<Event, AtomicLong> accessedPrice = new HashMap<>();
    private Map<Event, AtomicLong> bookedTickets = new HashMap<>();

    @AfterReturning(
            pointcut = "execution(public * com.epam.rd.movietheater.dao.EventDao.findByName(String))",
            returning = "retVal"
    )
    protected void countAccessingByName(List<Event> retVal) {
        retVal.forEach(event -> incrementCounterForEvent(event, accessesByName));
    }

    @AfterReturning(
            pointcut = "execution(public * com.epam.rd.movietheater.service.booking.BookingHelper.calculateTicketPrice(*))"
    )
    protected void countAccessesToPrice(JoinPoint joinPoint) {
        Ticket targetTicket = (Ticket) joinPoint.getArgs()[0];
        Event target = targetTicket.getEvent();
        incrementCounterForEvent(target, accessedPrice);
    }
    @AfterReturning(
            "execution(public * com.epam.rd.movietheater.service.booking.BookingHelper.bookTicket(*))"
    )
    protected void countBookedTicketsForParticularEvent(JoinPoint joinPoint) {
        Ticket targetTicket = (Ticket) joinPoint.getArgs()[0];
        Event target = targetTicket.getEvent();
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

    private Long getValue(Event event, Map<Event, AtomicLong> target) {
        return target.computeIfAbsent(event, k -> new AtomicLong()).get();
    }

    private void incrementCounterForEvent(Event event, Map<Event, AtomicLong> storage) {
        AtomicLong counter = storage.computeIfAbsent(event, k -> new AtomicLong());
        counter.incrementAndGet();
    }
}
