package com.epam.rd.movietheater.dao.impl.jpa.repository;

import com.epam.rd.movietheater.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByName(String name);
}
