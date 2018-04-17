package com.epam.rd.movietheater.service.discount.strategy;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PeriodicDiscountStrategy implements DiscountStrategy {

    @Value("${discount.strategy.step}")
    private int step;
    @Value("${discount.amount.periodic}")
    private int discountAmount;

    @Override
    public int calculateDiscount(Ticket ticket) {
        User user = ticket.getUser();
        return (user.getTickets().size() % 10 == 0) ? discountAmount : 0;
    }
}