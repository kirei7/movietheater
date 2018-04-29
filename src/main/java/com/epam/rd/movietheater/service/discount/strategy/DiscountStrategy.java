package com.epam.rd.movietheater.service.discount.strategy;

import com.epam.rd.movietheater.model.entity.Ticket;

import java.util.List;

public interface DiscountStrategy {
    List<Ticket> calculateDiscount(List<Ticket> tickets);
}
