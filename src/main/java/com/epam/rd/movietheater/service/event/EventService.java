package com.epam.rd.movietheater.service.event;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.service.IdentifiableEntityService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventService extends IdentifiableEntityService<Event> {
    Optional<Event> getByName(String name);
    List<Event> getForDateRange(LocalDate from, LocalDate to);
    List<Event> getNextEvents(LocalDate to);
}
