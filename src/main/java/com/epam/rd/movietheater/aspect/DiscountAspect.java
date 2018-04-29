package com.epam.rd.movietheater.aspect;

import com.epam.rd.movietheater.aspect.service.DiscountCounterService;
import com.epam.rd.movietheater.model.entity.Discount;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Aspect
@Component
public class DiscountAspect {

    private DiscountCounterService discountCounterService;

    @Autowired
    public DiscountAspect(DiscountCounterService discountCounterService) {
        this.discountCounterService = discountCounterService;
    }

    @AfterReturning(
            pointcut = "execution(public * com.epam.rd.movietheater.service.discount.DiscountService.assignDiscounts(*))",
            returning = "tickets"
    )
    protected void countGivenDiscount(JoinPoint joinPoint, List<Ticket> tickets) {
        tickets.forEach(ticket -> {
            Discount discount = ticket.getDiscount();
            if (discount.getAmount() > 0) {
                discountCounterService.incrementFor(discount.getType(), ticket.getUser());
            }
        });

    }

    public long getDiscountCount(Class<? extends DiscountStrategy> type) {
        return discountCounterService.getCount(type.getSimpleName());
    }
    public long getDiscountCount(Class<? extends DiscountStrategy> type, User user) {
            return discountCounterService.getCount(type.getSimpleName(), user);
    }

}
