package com.epam.rd.movietheater.service.auditorium;

import com.epam.rd.movietheater.model.Auditorium;
import com.epam.rd.movietheater.util.AuditoriumSource;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.*;
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
        when(auditoriumSource.getAuditoriums()).thenReturn(new HashSet<>(auditoriums));
        service = new AuditoriumServiceImpl(auditoriumSource);
    }

    @Test
    public void returnsTheSameSet() {
        assertTrue(new HashSet<>(service.getAll()).equals(new HashSet<>(auditoriums)));
    }

    @Test
    public void findByNameFindsAuditoriumByName() {
        Auditorium target = service.getAll().get(0);
        Auditorium finded = service.getByName(target.getName()).orElse(new Auditorium());

        assertEquals(target.getName(), finded.getName());
        assertEquals(target.getNumberOfSeats(), finded.getNumberOfSeats());
    }

}