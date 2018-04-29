package com.epam.rd.movietheater.service.discount;

import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DiscountServiceImpl implements DiscountService{

    private Set<DiscountStrategy> discountStrategies;

    @Autowired
    public DiscountServiceImpl(Set<DiscountStrategy> discountStrategies){
        this.discountStrategies = discountStrategies;
    }

    @Override
    public List<Ticket> assignDiscounts(List<Ticket> tickets) {
        discountStrategies.forEach(s -> s.calculateDiscount(tickets));
        return tickets;
    }

}