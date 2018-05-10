package com.epam.rd.movietheater.config;

import com.epam.rd.movietheater.model.entity.Auditorium;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.auditorium.AuditoriumService;
import com.epam.rd.movietheater.service.event.EventService;
import com.epam.rd.movietheater.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class TestDataConfig {

    private AuditoriumService auditoriumService;
    private EventService eventService;
    private UserService userService;

    @PostConstruct
    void setUp() {
        sampleEvents().forEach(eventService::save);
        userService.save(sampleUser());
    }

    @Bean
    public List<Auditorium> sampleAuditoriums() {
        return auditoriumService.getAll();
    }
    @Bean
    public List<Event> sampleEvents() {
        List<Event> events = new ArrayList<>();
        sampleAuditoriums().forEach(auditorium -> {
            events.add(createSampleEvent("Event 1", auditorium, 12, Event.Rating.HIGH));
            events.add(createSampleEvent("Event 2", auditorium, 15, Event.Rating.LOW));
        } );
        return events;
    }
    @Bean
    public User sampleUser() {
        return new User("Vlad", "1111", "vladyslav_sereda1@epam.com", LocalDate.now().minusYears(24));
    }

    @Autowired
    public TestDataConfig(AuditoriumService auditoriumService, EventService eventService, UserService userService) {
        this.auditoriumService = auditoriumService;
        this.eventService = eventService;
        this.userService = userService;
    }
    private Event createSampleEvent(String name, Auditorium auditorium, int hour, Event.Rating rating) {
        return new Event(name,
                LocalDateTime.now().withHour(hour),
                100,
                rating,
                auditorium);
    }
}
