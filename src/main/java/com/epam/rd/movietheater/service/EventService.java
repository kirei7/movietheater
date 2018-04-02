package com.epam.rd.movietheater.service;

import com.epam.rd.movietheater.entity.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventService {
    Event save(Event event);
    boolean remove(Event event);
    Optional<Event> getById(Long id);
    Optional<Event> getByName(String name);
    List<Event> getAll();

    List<Event> getForDateRange(LocalDate from, LocalDate to);
    List<Event> getNextEvents(LocalDate to);
}
