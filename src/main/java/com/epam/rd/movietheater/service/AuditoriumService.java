package com.epam.rd.movietheater.service;

import com.epam.rd.movietheater.entity.Auditorium;

import java.util.List;
import java.util.Optional;

public interface AuditoriumService {
    List<Auditorium> getAll();
    Optional<Auditorium> getByName(String name);
}
