package com.epam.rd.movietheater.service;

import com.epam.rd.movietheater.dao.impl.jpa.repository.AuditoriumRepository;
import com.epam.rd.movietheater.model.entity.Auditorium;
import com.epam.rd.movietheater.service.auditorium.AuditoriumServiceImpl;
import com.epam.rd.movietheater.service.auditorium.source.AuditoriumSource;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class AuditoriumServiceImplTest {

    private List<Auditorium> auditoriums;
    private AuditoriumServiceImpl service;

    public AuditoriumServiceImplTest() {
        auditoriums = Arrays.asList(
                new Auditorium("Red", 32),
                new Auditorium("Blue", 18)
        );
        AuditoriumSource auditoriumSource = Mockito.mock(AuditoriumSource.class);
        AuditoriumRepository auditoriumRepository = Mockito.mock(AuditoriumRepository.class);
        when(auditoriumSource.getAuditoriums()).thenReturn(new HashSet<>(auditoriums));
        when(auditoriumRepository.findAll()).thenReturn(auditoriums);
        service = new AuditoriumServiceImpl(auditoriumSource.getAuditoriums(), auditoriumRepository);
    }

    @Test
    public void returnsTheSameSet() {
        assertTrue(new HashSet<>(service.getAll()).equals(new HashSet<>(auditoriums)));
    }

    @Test
    public void findByNameFindsAuditoriumByName() {
        Auditorium target = service.getAll().get(0);
        Auditorium found = service.getByName(target.getName());

        assertEquals(target.getName(), found.getName());
        assertEquals(target.getNumberOfSeats(), found.getNumberOfSeats());
    }

}