package com.epam.rd.movietheater.aspect.dao.discount;

import com.epam.rd.movietheater.aspect.service.DiscountCounter;
import com.epam.rd.movietheater.model.entity.Discount;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;

import java.util.List;
import java.util.Optional;

public interface DiscountCounterDao {
    Optional<DiscountCounter> save(DiscountCounter discount);
    Optional<DiscountCounter> getDiscount(String type, User user);
    List<DiscountCounter> findByType(String type);
    List<DiscountCounter> findByUser(User user);

}
