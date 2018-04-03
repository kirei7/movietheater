package com.epam.rd.movietheater.service.impl;

import com.epam.rd.movietheater.dao.UserDao;
import com.epam.rd.movietheater.entity.User;
import com.epam.rd.movietheater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CommonEventService implements UserService {

    private UserDao userDao;

    @Autowired
    public CommonEventService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean remove(User user) {
        return userDao.remove(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }
}
