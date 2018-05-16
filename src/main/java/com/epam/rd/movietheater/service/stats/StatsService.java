package com.epam.rd.movietheater.service.stats;

import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;

import java.util.Map;

public interface StatsService {
    public Map<Class<? extends DiscountStrategy>, Long> getDiscountCounts();

    public Map<Class<? extends DiscountStrategy>, Long> getDiscountCounts(User user);
}
