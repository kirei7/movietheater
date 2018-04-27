package com.epam.rd.movietheater.model.entity;

import com.epam.rd.movietheater.service.discount.strategy.DiscountStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discount<T extends DiscountStrategy> {
    private DiscountId discountId;
    private Long timesGiven;

    public Discount(Class<T> type, User user, Long timesGiven) {
        discountId = new DiscountId(user, type);
        this.timesGiven = timesGiven;
    }

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class DiscountId {
        private User user;
        private Class<T> type;
    }
}
