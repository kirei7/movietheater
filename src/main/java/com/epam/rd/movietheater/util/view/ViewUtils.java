package com.epam.rd.movietheater.util.view;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Component("viewUtils")
public class ViewUtils {
    public List<SeatView> getSeatViews(Event event) {
        Set<Long> purchased = event.getReservedTickets().stream().mapToLong(Ticket::getSeat).boxed().collect(toSet());
        return LongStream
                .rangeClosed(0,event.getAuditorium().getNumberOfSeats())
                .boxed()
                .map(s -> new SeatView(s, purchased.contains(s), event.getAuditorium().getVipSeats().contains(s)))
                .collect(toList());
    }
}
