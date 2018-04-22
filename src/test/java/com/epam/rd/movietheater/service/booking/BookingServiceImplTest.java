package com.epam.rd.movietheater.service.booking;

import com.epam.rd.movietheater.model.entity.Auditorium;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.User;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

public class BookingServiceImplTest {

    private Auditorium auditorium;
    private Event event;
    private User user;
    private LocalDateTime airDate;

    @Test
    public void ticketsPriceCalculatedProperly() {

    }

    public BookingServiceImplTest() {
        auditorium = new Auditorium();
        auditorium.setName("Red");
        auditorium.setNumberOfSeats(30);
        auditorium.setVipSeats(new HashSet<>(Arrays.asList(10L,11L,12L)));

        airDate = LocalDateTime.now().plusHours(12);

        event = new Event();
        event.setName("event1");
        event.setRating(Event.Rating.HIGH);
        event.setAuditorium(auditorium);
        event.setBasePrice(100);
        event.setAirDate(airDate);

        user = new User();
        user.setFirstName("TestUser");
        user.setBirthday(LocalDate.now().minusYears(21));
    }
}