package com.epam.rd.movietheater.service.payment;

import com.epam.rd.movietheater.exception.NotEnoughMoneyException;
import com.epam.rd.movietheater.model.entity.UserAccount;
import com.epam.rd.movietheater.service.useraccount.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {

    private UserAccountService userAccountService;

    @Autowired
    public PaymentServiceImpl(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void withdrawFromAccount(UserAccount userAccount, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount for withdraw can't be less than or equal to zero");
        }
        BigDecimal availableSum = userAccount.getAmount();
        if (availableSum.compareTo(amount) < 0) {
            throw new NotEnoughMoneyException();
        }
        userAccount.setAmount(availableSum.subtract(amount));
        userAccountService.save(userAccount);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void refillAccount(UserAccount account, Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount for replenish can't be less than or equal to zero");
        }
        BigDecimal sum = account.getAmount().add(new BigDecimal(amount));
        account.setAmount(sum);
        userAccountService.save(account);
    }


}
