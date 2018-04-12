package com.epam.rd.movietheater.service.discount.strategy;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class BirthdayDiscountStrategy implements DiscountStrategy {

    @Value("${discount.amount.birthday}")
    private int discountAmount;

    @Override
    public int calculateDiscount(User user, Event event) {
        LocalDate now = LocalDate.now();
        long dif = ChronoUnit.DAYS.between(user.getBirthDay(), now.withYear(user.getBirthDay().getYear()));
        dif = Math.abs(dif);
        if (dif <= 5)
            return discountAmount;
        else
            return 0;
    }
}
