package com.epam.rd.movietheater.dao.impl;

import com.epam.rd.movietheater.dao.UserDao;
import com.epam.rd.movietheater.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUserDao extends AbstractInMemoryDao<User> implements UserDao {
    @Override
    public Optional<User> getByEmail(String email) {
        return storage.values().stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }
}
