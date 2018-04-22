package com.epam.rd.movietheater.dao.impl.inmemory;

import com.epam.rd.movietheater.dao.EventDao;
import com.epam.rd.movietheater.model.entity.Event;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public class InMemoryEventDao extends AbstractInMemoryDao<Event> implements EventDao {
    @Override
    public Optional<Event> findByName(String name) {
        return storage.values().stream().filter((event -> event.getName().equals(name))).findFirst();
    }
}
