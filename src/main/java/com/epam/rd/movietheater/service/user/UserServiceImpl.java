package com.epam.rd.movietheater.service.user;

import com.epam.rd.movietheater.dao.UserDao;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.AbstractUniqueEntityService;

import java.util.Optional;

public class UserServiceImpl extends AbstractUniqueEntityService<User, UserDao> implements UserService {

    public UserServiceImpl(UserDao dao) {
        super(dao);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return dao.getByEmail(email);
    }
}
