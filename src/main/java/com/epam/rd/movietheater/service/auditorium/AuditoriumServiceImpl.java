package com.epam.rd.movietheater.service.auditorium;

import com.epam.rd.movietheater.dao.impl.jpa.repository.AuditoriumRepository;
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

    private AuditoriumRepository auditoriumRepository;

    @Autowired
    public AuditoriumServiceImpl(@Value("#{AuditoriumSource.getAuditoriums()}") Set<Auditorium> auditoriums, AuditoriumRepository auditoriumRepository) {
        this.auditoriumRepository = auditoriumRepository;
        auditoriums.forEach(auditoriumRepository::save);
    }

    @Override
    public List<Auditorium> getAll() {
        return new ArrayList<>(
                auditoriumRepository.findAll()
        );
    }

    @Override
    public Auditorium getByName(String name) {
        return auditoriumRepository.findAll().stream().filter(e -> e.getName().equals(name)).findFirst().orElseThrow(AuditoriumNotFoundException::new);
    }
}
