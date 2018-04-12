package com.epam.rd.movietheater.service.discount;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DiscountServiceImpl implements DiscountService{

    private Set<DiscountStrategy> discountStrategies;

    @Autowired
    public DiscountServiceImpl(Set<DiscountStrategy> discountStrategies){
        this.discountStrategies = discountStrategies;
    }

    @Override
    public int getDiscount(User user, Event event) {
        return discountStrategies.stream().mapToInt(s -> s.calculateDiscount(user, event)).max().orElse(0);
    }
}
