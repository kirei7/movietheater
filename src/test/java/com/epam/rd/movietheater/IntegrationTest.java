package com.epam.rd.movietheater;

import com.epam.rd.movietheater.config.ApplicationConfig;
import com.epam.rd.movietheater.model.Auditorium;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.model.factory.EventFactory;
import com.epam.rd.movietheater.model.factory.UserFactory;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    public void bookingServiceTest() {
        Auditorium auditorium = auditoriumService.getAll().get(0);
        Event event = EventFactory.create("Sample event",
                LocalDateTime.now().withHour(12),
                100,
                Event.Rating.HIGH,
                auditorium);
        User user = UserFactory.create("Vlad", "Sereda", "vladyslav_sereda1@epam.com", LocalDate.now().minusYears(24));
        List<Ticket> tickets = bookingService.createTicketsForEvent(event, user, new long[]{5,6,7});
        assertEquals(3,tickets.size());
    }

    @Test
    public void auditoriumServiceTest() {
        assertEquals(2, auditoriumService.getAll().size());
    }

    @Test
    public void eventServiceTest() {
        List<Event> events = getSampleEvents();
        events.forEach(eventService::save);
        List<Event> saved = eventService.getAll();
        assertEquals(events.size(), saved.size());
    }

    private List<Event> getSampleEvents() {
        Auditorium auditorium = auditoriumService.getAll().get(0);
        List<Event> events = new ArrayList<>();
        events.add(
                EventFactory.create("Sample event",
                        LocalDateTime.now().withHour(12),
                        100,
                        Event.Rating.HIGH,
                        auditorium)
        );
        events.add(
                EventFactory.create("Sample event 2",
                        LocalDateTime.now().withHour(13),
                        100,
                        Event.Rating.HIGH,
                        auditorium)
        );
        events.add(
                EventFactory.create("Sample event 3",
                        LocalDateTime.now().withHour(14),
                        100,
                        Event.Rating.HIGH,
                        auditorium)
        );
        return events;
    }

}
