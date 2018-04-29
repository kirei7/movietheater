package com.epam.rd.movietheater.aspect.service;

import com.epam.rd.movietheater.aspect.dao.discount.DiscountCounterDao;
import com.epam.rd.movietheater.model.entity.Discount;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DiscountCounterServiceImpl implements DiscountCounterService {

    private DiscountCounterDao dao;

    @Autowired
    public DiscountCounterServiceImpl(DiscountCounterDao dao) {
        this.dao = dao;
    }

    @Override
    public void incrementFor(String type, User user) {
        Optional<DiscountCounter> opt = dao.getDiscount(type, user);
        DiscountCounter discount = opt.orElse(new DiscountCounter(type, user, 0L));
        discount.setTimesGiven(discount.getTimesGiven() + 1);
        dao.save(discount);
    }

    @Override
    public Long getCount(String type) {
        return dao.findByType(type).stream().mapToLong(DiscountCounter::getTimesGiven).sum();
    }

    @Override
    public Long getCount(String type, User user) {
        return dao.getDiscount(type, user).orElse(new DiscountCounter()).getTimesGiven();
    }
}
