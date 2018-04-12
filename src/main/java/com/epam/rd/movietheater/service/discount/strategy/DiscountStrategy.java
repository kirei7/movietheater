package com.epam.rd.movietheater.service.discount.strategy;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.User;

public interface DiscountStrategy {
    int calculateDiscount(User user, Event event);
}
