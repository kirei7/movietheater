package com.epam.rd.movietheater.service.discount.strategy;

import com.epam.rd.movietheater.model.entity.Discount;

public abstract class AbstractDisountStrategy implements DiscountStrategy {
    protected Discount getMaxDiscount(Discount presentDiscount, Discount newDiscount) {
        return (presentDiscount.getAmount() >= newDiscount.getAmount()) ? presentDiscount : newDiscount;
    }
}
