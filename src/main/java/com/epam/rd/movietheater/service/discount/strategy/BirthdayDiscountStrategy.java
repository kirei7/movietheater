package com.epam.rd.movietheater.service.discount.strategy;

import com.epam.rd.movietheater.model.entity.Discount;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Component
public class BirthdayDiscountStrategy extends AbstractDisountStrategy implements DiscountStrategy {

    @Value("${discount.amount.birthday}")
    private int discountAmount;

    @Override
    public List<Ticket> calculateDiscount(List<Ticket> tickets) {
        LocalDate now = LocalDate.now();
        tickets.forEach(ticket -> {
            int calculatedDiscount = 0;
            User user = ticket.getUser();
            long dif;
            if (!isUserValid(user))
                dif = Long.MAX_VALUE;
            else
                dif = ChronoUnit.DAYS.between(user.getBirthday(), now.withYear(user.getBirthday().getYear()));
            dif = Math.abs(dif);
            if (dif <= 5)
                calculatedDiscount = discountAmount;
            if (calculatedDiscount > Optional.ofNullable(ticket.getDiscount()).orElse(new Discount()).getAmount()) {
                ticket.setDiscount(
                        getMaxDiscount(ticket.getDiscount(), new Discount(getClass().getSimpleName(), ticket, discountAmount))
                );
            }
        });
        return tickets;
    }

    private boolean isUserValid(User user) {
        return user.getBirthday() != null;
    }
}
