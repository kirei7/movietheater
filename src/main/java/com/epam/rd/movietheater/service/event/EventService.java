package com.epam.rd.movietheater.service.event;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.service.UniqueEntityService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventService extends UniqueEntityService<Event> {
    Optional<Event> getByName(String name);
    List<Event> getForDateRange(LocalDateTime from, LocalDateTime to);
    List<Event> getNextEvents(LocalDateTime to);
}
