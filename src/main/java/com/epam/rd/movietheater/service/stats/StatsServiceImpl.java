package com.epam.rd.movietheater.service.stats;

import com.epam.rd.movietheater.aspect.DiscountAspect;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StatsServiceImpl implements StatsService {

    private List<DiscountStrategy> discountStrategies;
    private DiscountAspect discountAspect;

    @Autowired
    public StatsServiceImpl(List<DiscountStrategy> discountStrategies, DiscountAspect discountAspect) {
        this.discountStrategies = discountStrategies;
        this.discountAspect = discountAspect;
    }

    @Override
    public Map<Class<? extends DiscountStrategy>, Long> getDiscountCounts() {
        Map<Class<? extends DiscountStrategy>, Long> result = new HashMap<>();
        discountStrategies.forEach(ds -> result.put(ds.getClass(), discountAspect.getDiscountCount(ds.getClass())));
        return result;
    }

    @Override
    public Map<Class<? extends DiscountStrategy>, Long> getDiscountCounts(User user) {
        Map<Class<? extends DiscountStrategy>, Long> result = new HashMap<>();
        discountStrategies.forEach(ds -> result.put(ds.getClass(), discountAspect.getDiscountCount(ds.getClass(), user)));
        return result;
    }
}
