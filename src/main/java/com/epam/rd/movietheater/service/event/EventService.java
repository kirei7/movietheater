package com.epam.rd.movietheater.service.event;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.service.IdentifiableEntityService;

import java.time.LocalDate;
import java.util.List;

public interface EventService extends IdentifiableEntityService<Event> {
    List<Event> getByName(String name);
    List<Event> getForDateRange(LocalDate from, LocalDate to);
    List<Event> getNextEvents(LocalDate to);
}
