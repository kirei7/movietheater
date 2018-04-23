package com.epam.rd.movietheater.dao;

import com.epam.rd.movietheater.model.entity.Event;

import java.util.List;

public interface EventDao extends IdentifiableDao<Event> {
    List<Event> findByName(String name);
}
