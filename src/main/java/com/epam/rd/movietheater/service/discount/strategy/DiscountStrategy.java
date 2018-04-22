package com.epam.rd.movietheater.service.discount.strategy;

import com.epam.rd.movietheater.model.entity.Ticket;

public interface DiscountStrategy {
    int calculateDiscount(Ticket ticket);
}
