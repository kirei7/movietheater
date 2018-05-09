package com.epam.rd.movietheater.service.payment;

import com.epam.rd.movietheater.model.entity.UserAccount;

import java.math.BigDecimal;

public interface PaymentService {
    void withdrawFromAccount(UserAccount userAccount, BigDecimal amount);

    void refillAccount(UserAccount userAccount, Long amount);
}
