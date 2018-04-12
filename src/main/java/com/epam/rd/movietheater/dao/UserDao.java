package com.epam.rd.movietheater.dao;

import com.epam.rd.movietheater.model.entity.User;

import java.util.Optional;

public interface UserDao extends UniqueEntityDao<User> {
    Optional<User> getByEmail(String email);
}
