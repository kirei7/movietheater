package com.epam.rd.movietheater;

import com.epam.rd.movietheater.aspect.DiscountAspect;
import com.epam.rd.movietheater.config.IntegrationTestConfig;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.booking.BookingService;
import com.epam.rd.movietheater.service.discount.strategy.BirthdayDiscountStrategy;
import com.epam.rd.movietheater.service.discount.strategy.PeriodicDiscountStrategy;
import com.epam.rd.movietheater.service.user.UserService;
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
public class DiscountAspectTest {

    @Autowired
    private DiscountAspect discountAspect;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;

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

        userService.save(user);
        userService.save(user2);
        userService.save(user3);

        bookingService.createTicketsForEvent(events.get(0), user, new long[]{1});
        bookingService.createTicketsForEvent(events.get(0), user2, new long[]{2});
        bookingService.createTicketsForEvent(events.get(0), user3, new long[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13});

        assertEquals(12, discountAspect.getDiscountCount(BirthdayDiscountStrategy.class));
        assertEquals(1, discountAspect.getDiscountCount(PeriodicDiscountStrategy.class));
        assertEquals(1, discountAspect.getDiscountCount(BirthdayDiscountStrategy.class, user));
    }
}
