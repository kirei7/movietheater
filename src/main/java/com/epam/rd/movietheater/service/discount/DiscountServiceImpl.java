package com.epam.rd.movietheater.service.discount;

import com.epam.rd.movietheater.model.entity.Ticket;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DiscountServiceImpl implements DiscountService{

    private Set<DiscountStrategy> discountStrategies;

    @Autowired
    public DiscountServiceImpl(Set<DiscountStrategy> discountStrategies){
        this.discountStrategies = discountStrategies;
    }

    @Override
    public void assignDiscounts(List<Ticket> tickets) {
        User user = new User(tickets.get(0).getUser());
        tickets.forEach(ticket -> {
            ticket.setDiscount(getDiscount(ticket));
            user.addTicket(ticket);
        });
    }

    private int getDiscount(Ticket ticket) {
        return discountStrategies.stream().mapToInt(s -> s.calculateDiscount(ticket)).max().orElse(0);
    }
}
