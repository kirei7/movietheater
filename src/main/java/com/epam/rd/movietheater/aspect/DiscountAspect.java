package com.epam.rd.movietheater.aspect;

import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


@Aspect
@Component
public class DiscountAspect {
    private Map<User, Map<Class<? extends DiscountStrategy>, AtomicLong>> discountCount = new HashMap<>();
    @AfterReturning(
            pointcut = "execution(public * com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy.calculateDiscount(com.epam.rd.movietheater.model.entity.Ticket)) && args(ticket)",
            returning = "discountAmount"
    )
    protected void countGivenDiscount(JoinPoint joinPoint, Ticket ticket, int discountAmount) {
        if (discountAmount <= 0)
            return;
        DiscountStrategy strategy = (DiscountStrategy) joinPoint.getTarget();
        incrementForUser(ticket.getUser(), strategy.getClass());
    }
    private void incrementForUser(User user, Class<? extends DiscountStrategy> strategyType) {
        Map<Class<? extends DiscountStrategy>, AtomicLong> userMap = getUserMap(user);
        AtomicLong counter = userMap.computeIfAbsent(strategyType, k -> new AtomicLong());
        counter.incrementAndGet();
    }

    private Map<Class<? extends DiscountStrategy>, AtomicLong> getUserMap(User user) {
        return discountCount.computeIfAbsent(user, k -> new HashMap<>());
    }

    public long getDiscountCount(Class<? extends DiscountStrategy> type) {
        return discountCount.values().stream()
                .mapToLong(m -> m.entrySet().stream()
                        .filter(e -> e.getKey().equals(type))
                        .mapToLong(e -> Optional.of(e.getValue()).orElse(new AtomicLong()).get())
                        .sum()
                ).sum();
    }
    public long getDiscountCount(Class<? extends DiscountStrategy> type, User user) {
            return discountCount
                    .computeIfAbsent(user, k -> new HashMap<>())
                    .computeIfAbsent(type, k -> new AtomicLong()).get();
    }

}
