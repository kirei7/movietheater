package com.epam.rd.movietheater.service;

import com.epam.rd.movietheater.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    boolean remove(User user);
    Optional<User> getById(Long id);
    Optional<User> getUserByEmail(String email);
    List<User> getAll();
}
