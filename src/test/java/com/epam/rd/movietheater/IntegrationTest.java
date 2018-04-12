package com.epam.rd.movietheater;

import com.epam.rd.movietheater.config.ApplicationConfig;
import com.epam.rd.movietheater.model.Auditorium;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.service.auditorium.AuditoriumService;
import com.epam.rd.movietheater.service.booking.BookingService;
import com.epam.rd.movietheater.service.event.EventService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
public class IntegrationTest {

    private final Log logger = LogFactory.getLog(IntegrationTest.class);

    @Autowired
    private BookingService bookingService;
    @Autowired
    private EventService eventService;
    @Autowired
    private AuditoriumService auditoriumService;

    @Test
    public void test() {
        /*Event event = new Event();
        event.setName("Test event");
        event.setAirDate(LocalDateTime.now());
        event.setBasePrice(20);
        event.setRating(Event.Rating.HIGH);
        event.setAuditorium();*/
        logger.info(auditoriumService.getAll());
    }
}
