package com.epam.rd.movietheater.service.auditorium;

import com.epam.rd.movietheater.exception.AuditoriumNotFoundException;
import com.epam.rd.movietheater.model.entity.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    private final Set<Auditorium> auditoriums;

    @Autowired
    public AuditoriumServiceImpl(@Value("#{AuditoriumSource.getAuditoriums()}") Set<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    @Override
    public List<Auditorium> getAll() {
        return new ArrayList<>(auditoriums);
    }

    @Override
    public Auditorium getByName(String name) {
        return auditoriums.stream().filter(e -> e.getName().equals(name)).findFirst().orElseThrow(AuditoriumNotFoundException::new);
    }
}
