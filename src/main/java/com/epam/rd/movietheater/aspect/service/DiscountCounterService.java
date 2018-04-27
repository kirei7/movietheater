package com.epam.rd.movietheater.aspect.service;

import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;

public interface DiscountCounterService {
    <T extends DiscountStrategy> void incremetFor(Class<T> type, User user);
    <T extends DiscountStrategy> Long getCount(Class<T> type);
    <T extends DiscountStrategy> Long getCount(Class<T> type, User user);
}
