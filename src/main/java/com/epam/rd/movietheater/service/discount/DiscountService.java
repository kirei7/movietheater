package com.epam.rd.movietheater.service.discount;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.User;

import java.time.LocalDate;

public interface DiscountService {
    int getDiscount(User user, Event event);
}
