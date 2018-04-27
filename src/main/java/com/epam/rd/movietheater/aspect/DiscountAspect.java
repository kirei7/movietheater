package com.epam.rd.movietheater.aspect;

import com.epam.rd.movietheater.aspect.service.DiscountCounterService;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DiscountAspect {

    private DiscountCounterService discountCounterService;

    @Autowired
    public DiscountAspect(DiscountCounterService discountCounterService) {
        this.discountCounterService = discountCounterService;
    }

    @AfterReturning(
            pointcut = "execution(public * com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy.calculateDiscount(com.epam.rd.movietheater.model.entity.Ticket)) && args(ticket)",
            returning = "discountAmount"
    )
    protected void countGivenDiscount(JoinPoint joinPoint, Ticket ticket, int discountAmount) {
        if (discountAmount <= 0)
            return;
        DiscountStrategy strategy = (DiscountStrategy) joinPoint.getTarget();
        discountCounterService.incremetFor(strategy.getClass(), ticket.getUser());
    }

    public long getDiscountCount(Class<? extends DiscountStrategy> type) {
        return discountCounterService.getCount(type);
    }
    public long getDiscountCount(Class<? extends DiscountStrategy> type, User user) {
            return discountCounterService.getCount(type, user);
    }

}
