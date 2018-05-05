package com.epam.rd.movietheater.service.auditorium;

import com.epam.rd.movietheater.model.entity.Auditorium;

import java.util.List;

public interface AuditoriumService {
    List<Auditorium> getAll();

    Auditorium getByName(String name);
}
