package com.epam.rd.movietheater.service.auditorium;

import com.epam.rd.movietheater.model.entity.Auditorium;

import java.util.List;
import java.util.Optional;

public interface AuditoriumService {
    List<Auditorium> getAll();
    Optional<Auditorium> getByName(String name);
}
