package com.epam.rd.movietheater.dao;

import com.epam.rd.movietheater.model.entity.Event;

import java.util.Optional;

public interface EventDao extends UniqueEntityDao<Event> {
    Optional<Event> findByName(String name);
}
