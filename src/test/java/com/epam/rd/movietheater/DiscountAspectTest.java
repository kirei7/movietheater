package com.epam.rd.movietheater;

import com.epam.rd.movietheater.aspect.CountAspect;
import com.epam.rd.movietheater.aspect.DiscountAspect;
import com.epam.rd.movietheater.config.IntegrationTestConfig;
import com.epam.rd.movietheater.dao.EventDao;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.model.factory.TicketFactory;
import com.epam.rd.movietheater.service.booking.BookingHelper;
import com.epam.rd.movietheater.service.booking.BookingService;
import com.epam.rd.movietheater.service.discount.strategy.BirthdayDiscountStrategy;
import com.epam.rd.movietheater.service.discount.strategy.PeriodicDiscountStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {IntegrationTestConfig.class})
public class DiscountAspectTest {

    @Autowired
    private DiscountAspect discountAspect;
    @Autowired
    private BookingService bookingService;

    @Autowired
    @Qualifier("sampleEvents")
    private List<Event> events;
    @Autowired
    @Qualifier("sampleUser")
    private User user;

    @Test
    public void testDiscountCounter() {
        user.setId(1L);
        User user2 = new User(user);
        user2.setFirstName("Second");
        user2.setId(2L);
        User user3 = new User(user);
        user3.setFirstName("Third");
        user3.setId(3L);

        bookingService.createTicketsForEvent(events.get(0), user, new long[]{1});
        bookingService.createTicketsForEvent(events.get(0), user2, new long[]{2});
        bookingService.createTicketsForEvent(events.get(0), user3, new long[]{3});

        assertEquals(3, discountAspect.getDiscountCount(BirthdayDiscountStrategy.class));
        assertEquals(0, discountAspect.getDiscountCount(PeriodicDiscountStrategy.class));
        assertEquals(1, discountAspect.getDiscountCount(BirthdayDiscountStrategy.class, user));
    }
}
