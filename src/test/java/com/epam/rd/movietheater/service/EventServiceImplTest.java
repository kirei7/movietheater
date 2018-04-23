package com.epam.rd.movietheater.service;

import com.epam.rd.movietheater.dao.EventDao;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.service.event.EventServiceImpl;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EventServiceImplTest {

    private EventServiceImpl eventService;

    private Event sampleEvent;
    private LocalDateTime baseTime;

    public EventServiceImplTest() {
        baseTime = LocalDateTime.now();
        sampleEvent = createSampleEvent();
        EventDao dao = mockDao();
        eventService = new EventServiceImpl(dao);
    }

    @Test
    public void findEventByName() {
        Event result = eventService.getByName(sampleEvent.getName()).get(0);
        assertEquals(sampleEvent, result);
    }

    @Test
    public void findEventsInTimePeriod() {
        LocalDate from = baseTime.toLocalDate().minusDays(1);
        LocalDate to = baseTime.toLocalDate().plusDays(2);

        List<Event> events = eventService.getForDateRange(from,to);
        assertEquals(3, events.size());
    }

    @Test
    public void findEventsToDate() {
        assertEquals(3,eventService.getNextEvents(baseTime.toLocalDate().plusDays(2)).size());
    }

    private EventDao mockDao() {
        EventDao dao = mock(EventDao.class);
        when(dao.findByName(sampleEvent.getName())).thenReturn(Collections.singletonList(sampleEvent));
        when(dao.findAll()).thenReturn(Arrays.asList(
                newWithTimeOffset(sampleEvent, -12),
                sampleEvent,
                newWithTimeOffset(sampleEvent, 24),
                newWithTimeOffset(sampleEvent, 48),
                newWithTimeOffset(sampleEvent, 72)
        ));
        return dao;
    }

    private Event createSampleEvent() {
        Event result = new Event();
        String eventName = "Clockwork orange";
        result.setId(1351L);
        result.setName(eventName);
        result.setBasePrice(52.12);
        result.setRating(Event.Rating.MID);
        result.setAirDate(baseTime);
        return result;
    }

    private Event newWithTimeOffset(Event target, int plusHours) {
        Event result = new Event();
        result.setName(target.getName());
        result.setAuditorium(target.getAuditorium());
        result.setRating(target.getRating());
        result.setReservedTickets(new HashSet<>(target.getReservedTickets()));
        result.setAirDate(target.getAirDate().plusHours(plusHours));
        return result;
    }
}