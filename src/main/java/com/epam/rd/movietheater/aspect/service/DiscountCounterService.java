package com.epam.rd.movietheater.aspect.service;

import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;

public interface DiscountCounterService {
    void incrementFor(String type, User user);
    Long getCount(String type);
    Long getCount(String type, User user);
}
