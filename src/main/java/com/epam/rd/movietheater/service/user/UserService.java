package com.epam.rd.movietheater.service.user;

import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.UniqueEntityService;

import java.util.Optional;

public interface UserService extends UniqueEntityService<User> {
    Optional<User> getUserByEmail(String email);
}
