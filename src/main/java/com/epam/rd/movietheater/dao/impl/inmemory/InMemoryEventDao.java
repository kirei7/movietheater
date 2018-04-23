package com.epam.rd.movietheater.dao.impl.inmemory;

import com.epam.rd.movietheater.dao.EventDao;
import com.epam.rd.movietheater.model.entity.Event;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class InMemoryEventDao extends AbstractInMemoryDao<Event> implements EventDao {
    @Override
    public List<Event> findByName(String name) {
        return storage.values().stream().filter((event -> event.getName().equals(name))).collect(toList());
    }
}
