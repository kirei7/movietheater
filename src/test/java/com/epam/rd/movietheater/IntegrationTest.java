package com.epam.rd.movietheater;

import com.epam.rd.movietheater.config.IntegrationTestConfig;
import com.epam.rd.movietheater.dao.TicketDao;
import com.epam.rd.movietheater.model.entity.Auditorium;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.auditorium.AuditoriumService;
import com.epam.rd.movietheater.service.booking.BookingService;
import com.epam.rd.movietheater.service.event.EventService;
import com.epam.rd.movietheater.service.user.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {IntegrationTestConfig.class})
@DirtiesContext
public class IntegrationTest {

    private final Log logger = LogFactory.getLog(IntegrationTest.class);

    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private EventService eventService;
    @Autowired
    private AuditoriumService auditoriumService;
    @Autowired
    private TicketDao ticketDao;

    @Autowired
    @Qualifier("sampleAuditoriums")
    private List<Auditorium> auditoriums;
    @Autowired
    @Qualifier("sampleEvents")
    private List<Event> events;
    @Autowired
    @Qualifier("sampleUser")
    private User user;

    @Before
    public void setUp() {
        events.forEach(eventService::save);
        userService.save(user);
    }

    @Test
    public void bookingServiceTest() {
        Auditorium auditorium = auditoriums.get(0);
        Event event = events.get(0);
        List<Ticket> tickets = bookingService.createTicketsForEvent(event, user, new long[]{5, 6});
        assertEquals(2, tickets.size());

        bookingService.bookTickets(tickets);
        List<Ticket> purchased = bookingService.getPurchasedTicketsForEvent(event);
        assertEquals(tickets, purchased);

        List<Ticket> tickets2 = bookingService.createTicketsForEvent(events.get(1), user, new long[]{5, 6});
        bookingService.bookTickets(tickets2);
        List<Ticket> purchased2 = bookingService.getPurchasedTicketsForEvent(events.get(1));
        assertEquals(tickets2.size(), bookingService.getPurchasedTicketsForEvent(events.get(1)).size());

        assertEquals(tickets.size() + tickets2.size(), ticketDao.findAll().size());
    }

    @Test
    public void userServiceTest() {
        assertEquals(user, userService.getUserByEmail(user.getEmail()).orElse(new User()));
    }

    @Test
    public void auditoriumServiceTest() {
        assertEquals(2, auditoriumService.getAll().size());
    }

    @Test
    public void eventServiceTest() {
        events.forEach(eventService::save);
        List<Event> saved = eventService.getAll();
        assertEquals(events.size(), saved.size());
    }

}
