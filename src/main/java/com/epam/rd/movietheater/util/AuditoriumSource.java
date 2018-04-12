package com.epam.rd.movietheater.util;

import com.epam.rd.movietheater.model.Auditorium;

import java.util.Set;

public interface AuditoriumSource {
    Set<Auditorium> getAuditoriums();
}
