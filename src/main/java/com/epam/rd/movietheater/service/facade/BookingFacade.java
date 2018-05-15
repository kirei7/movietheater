package com.epam.rd.movietheater.service.facade;

import com.epam.rd.movietheater.model.Order;
import com.epam.rd.movietheater.model.entity.User;

public interface BookingFacade {

    Order previewOrder(Long eventId, User user, long[] seats);

    Order buyTickets(Long eventId, User user, long[] seats);

    void replenishAccount(User user, Long amount);
}
