package com.epam.rd.movietheater.model.factory;

import com.epam.rd.movietheater.model.Auditorium;
import com.epam.rd.movietheater.model.entity.Event;

import java.time.LocalDateTime;

public abstract class EventFactory {
    public static Event create(String name, LocalDateTime airDate, double basePrice, Event.Rating rating, Auditorium auditorium) {
        Event event = new Event();
        event.setName(name);
        event.setAirDate(airDate);
        event.setBasePrice(basePrice);
        event.setRating(rating);
        event.setAuditorium(auditorium);
        return event;
    }
}
