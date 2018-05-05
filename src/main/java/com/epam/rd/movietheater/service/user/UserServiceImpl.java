package com.epam.rd.movietheater.service.user;

import com.epam.rd.movietheater.dao.UserDao;
import com.epam.rd.movietheater.exception.UserNotFoundException;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.AbstractIdentifiableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractIdentifiableService<User, UserDao> implements UserService {

    @Autowired
    public UserServiceImpl(UserDao dao) {
        super(dao);
    }

    @Override
    public User getUserByEmail(String email) {
        return dao.getByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByNickName(String nickName) {
        return dao.getByNickName(nickName).orElseThrow(UserNotFoundException::new);
    }
}
