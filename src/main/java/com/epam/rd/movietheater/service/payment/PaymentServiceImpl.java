package com.epam.rd.movietheater.service.payment;

import com.epam.rd.movietheater.model.entity.UserAccount;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public class PaymentServiceImpl implements PaymentService {

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void withdrawFromAccount(UserAccount userAccount, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount for withdraw can't be less than or equal to zero");
        }
        BigDecimal availableSum = userAccount.getAmount();
        if (availableSum.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Not enough money on account");
        }
        userAccount.setAmount(availableSum.min(amount));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void refillAccount(UserAccount account, Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount for replenish can't be less than or equal to zero");
        }
        BigDecimal sum = account.getAmount().add(new BigDecimal(amount));
        account.setAmount(sum);
    }


}
