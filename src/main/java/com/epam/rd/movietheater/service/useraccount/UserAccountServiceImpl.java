package com.epam.rd.movietheater.service.useraccount;

import com.epam.rd.movietheater.dao.UserAccountDao;
import com.epam.rd.movietheater.model.entity.UserAccount;
import com.epam.rd.movietheater.service.AbstractIdentifiableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl extends AbstractIdentifiableService<UserAccount, UserAccountDao> implements UserAccountService {
    @Autowired
    public UserAccountServiceImpl(UserAccountDao dao) {
        super(dao);
    }
}
