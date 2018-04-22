package com.epam.rd.movietheater.dao.impl.jpa;

import com.epam.rd.movietheater.dao.EventDao;
import com.epam.rd.movietheater.dao.impl.jpa.repository.EventRepository;
import com.epam.rd.movietheater.model.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventDaoImpl implements EventDao {

    private EventRepository eventRepository;

    @Autowired
    public EventDaoImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<Event> findByName(String name) {
        return eventRepository.findByName(name);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public boolean remove(Event event) {
        eventRepository.delete(event);
        return eventRepository.findById(event.getId()).isPresent();
    }

    @Override
    public Optional<Event> getById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }
}
