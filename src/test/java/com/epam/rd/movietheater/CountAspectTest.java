package com.epam.rd.movietheater;

import com.epam.rd.movietheater.aspect.CountAspect;
import com.epam.rd.movietheater.config.IntegrationTestConfig;
import com.epam.rd.movietheater.dao.EventDao;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.booking.BookingHelper;
import com.epam.rd.movietheater.service.booking.BookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {IntegrationTestConfig.class})
@DirtiesContext
public class CountAspectTest {

    @Autowired
    private CountAspect countAspect;

    @Autowired
    private EventDao eventDao;
    @Autowired
    private BookingHelper bookingHelper;
    @Autowired
    private BookingService bookingService;

    @Autowired
    @Qualifier("sampleEvents")
    private List<Event> events;
    @Autowired
    @Qualifier("sampleUser")
    private User user;


    @PostConstruct
    public void init() {
        eventDao.save(events.get(0));
    }

    @Test
    public void testCountingAccessesByName() {
        int n = 4;
        Event event = events.get(0);
        for (int i = 0; i < n; i++) {
            eventDao.findByName(event.getName());
        }
        assertEquals(n, countAspect.getAccessesByName(event));
    }

    @Test
    public void testCountingAccessesByPrice() {
        Event event = events.get(0);
        List<Ticket> tickets = Stream.of(5L,6L,7L).map(seat -> new Ticket(event, user, seat)).collect(toList());
        tickets.forEach(bookingHelper::calculateTicketPrice);
        assertEquals(tickets.size(), countAspect.getAccessesToPrice(event));
    }

    @Test
    public void testCountingBookedTicketsForParticularEvent() {
        Event event = events.get(0);
        List<Ticket> tickets = LongStream.range(0,8).mapToObj(seat -> new Ticket(event, user, seat)).collect(toList());
        tickets.forEach(bookingHelper::bookTicket);
        assertEquals(tickets.size(), countAspect.getNumberOfBookedTickets(event));
    }

    @Test
    public void testCountDiscountsForUser() {
        User user = new User("John", "Doe", "eee@ee.com", LocalDate.now().minusYears(20));
        List<Ticket> tickets = bookingService.createTicketsForEvent(events.get(0), user, LongStream.range(0,11).toArray());
    }
}
