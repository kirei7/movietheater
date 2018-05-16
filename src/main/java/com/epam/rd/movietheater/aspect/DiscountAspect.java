package com.epam.rd.movietheater.aspect;

import com.epam.rd.movietheater.aspect.service.DiscountCounterService;
import com.epam.rd.movietheater.model.Order;
import com.epam.rd.movietheater.model.entity.Discount;
import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.security.UserRole;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Aspect
@Component
public class DiscountAspect {

    private DiscountCounterService discountCounterService;

    @Autowired
    public DiscountAspect(DiscountCounterService discountCounterService) {
        this.discountCounterService = discountCounterService;
    }

    @AfterReturning(
            pointcut = "execution(public * com.epam.rd.movietheater.service.facade.BookingFacade.buyTickets(Long, com.epam.rd.movietheater.model.entity.User, long[]))",
            returning = "order"
    )
    protected void countGivenDiscount(JoinPoint joinPoint, Order order) {
        List<Ticket> tickets = order.getBookedTickets();
        if (tickets.get(0).getUser().getRoles().contains(UserRole.BOOKING_MANAGER))
            return;
        tickets.forEach(ticket -> {
            Discount discount = ticket.getDiscount();
            if (discount.getAmount() > 0) {
                discountCounterService.incrementFor(discount.getType(), ticket.getUser());
            }
        });

    }

    public long getDiscountCount(Class<? extends DiscountStrategy> type) {
        return Optional.ofNullable(discountCounterService.getCount(type.getSimpleName())).orElse(0L);
    }
    public long getDiscountCount(Class<? extends DiscountStrategy> type, User user) {
        return Optional.ofNullable(discountCounterService.getCount(type.getSimpleName(), user)).orElse(0L);
    }

}
