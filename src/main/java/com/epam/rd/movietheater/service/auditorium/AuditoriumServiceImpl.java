package com.epam.rd.movietheater.service.auditorium;

import com.epam.rd.movietheater.model.Auditorium;
import com.epam.rd.movietheater.service.auditorium.AuditoriumService;
import com.epam.rd.movietheater.util.AuditoriumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    private final Set<Auditorium> auditoriums;

    @Autowired
    public AuditoriumServiceImpl(AuditoriumSource auditoriumSource) {
        this.auditoriums = Collections.unmodifiableSet(auditoriumSource.getAuditoriums());
    }

    @Override
    public List<Auditorium> getAll() {
        return new ArrayList<>(auditoriums);
    }

    @Override
    public Optional<Auditorium> getByName(String name) {
        return auditoriums.stream().filter(e -> e.getName().equals(name)).findFirst();
    }
}
