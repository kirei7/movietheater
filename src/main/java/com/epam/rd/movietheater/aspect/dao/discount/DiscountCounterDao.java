package com.epam.rd.movietheater.aspect.dao.discount;

import com.epam.rd.movietheater.model.entity.Discount;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;

import java.util.List;
import java.util.Optional;

public interface DiscountCounterDao {
    Optional<Discount> save(Discount discount);
    <T extends DiscountStrategy> Optional<Discount> getDiscount(Class<T> type, User user);
    <T extends DiscountStrategy> List<Discount> findByType(Class<T> type);
    List<Discount> findByUser(User user);
}
