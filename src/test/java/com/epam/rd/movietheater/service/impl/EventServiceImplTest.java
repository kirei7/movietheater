package com.epam.rd.movietheater.service.impl;

import com.epam.rd.movietheater.dao.EventDao;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.service.event.EventServiceImpl;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
        Event result = eventService.getByName(sampleEvent.getName()).orElse(null);
        assertEquals(sampleEvent, result);
    }

    @Test
    public void findEventsInTimePeriod() {
        LocalDate from = baseTime.minusHours(24);
        LocalDate to = baseTime.plusHours(25);

        assertEquals(3, eventService.getForDateRange(from,to).size());
    }

    @Test
    public void findEventsToDate() {
        assertEquals(2,eventService.getNextEvents(baseTime.plusHours(49)).size());
    }

    private EventDao mockDao() {
        EventDao dao = mock(EventDao.class);
        when(dao.findByName(sampleEvent.getName())).thenReturn(Optional.of(sampleEvent));
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
        result.setAirDate(target.getAirDate().plusHours(plusHours));
        return result;
    }
}