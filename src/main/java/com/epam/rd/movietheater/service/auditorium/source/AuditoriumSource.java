package com.epam.rd.movietheater.service.auditorium.source;

import com.epam.rd.movietheater.model.entity.Auditorium;

import java.util.Set;

public interface AuditoriumSource {
    Set<Auditorium> getAuditoriums();
}
