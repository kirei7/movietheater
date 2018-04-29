package com.epam.rd.movietheater.aspect.service;

import com.epam.rd.movietheater.model.entity.Discount;
import com.epam.rd.movietheater.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class DiscountCounter {
    private Discount discount;
    private User user;
    private Long timesGiven;

    public DiscountCounter(String type, User user, Long timesGiven) {
        discount = new Discount(type, null, 0);
        this.user = user;
        this.timesGiven = timesGiven;
    }
}
