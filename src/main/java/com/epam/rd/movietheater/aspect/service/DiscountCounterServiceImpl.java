package com.epam.rd.movietheater.aspect.service;

import com.epam.rd.movietheater.aspect.dao.discount.DiscountCounterDao;
import com.epam.rd.movietheater.model.entity.Discount;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscountCounterServiceImpl implements DiscountCounterService {

    private DiscountCounterDao dao;

    @Autowired
    public DiscountCounterServiceImpl(DiscountCounterDao dao) {
        this.dao = dao;
    }

    @Override
    public <T extends DiscountStrategy> void incremetFor(Class<T> type, User user) {
        Discount discount = dao.getDiscount(type, user).orElse(new Discount(type, user, 0L));
        discount.setTimesGiven(discount.getTimesGiven() + 1);
        dao.save(discount);
    }

    @Override
    public <T extends DiscountStrategy> Long getCount(Class<T> type) {
        return dao.findByType(type).stream().mapToLong(Discount::getTimesGiven).sum();
    }

    @Override
    public <T extends DiscountStrategy> Long getCount(Class<T> type, User user) {
        return dao.findByUser(user).stream().filter(v -> v.getDiscountId().getType().equals(type)).mapToLong(v -> v.getTimesGiven()).sum();
    }
}
