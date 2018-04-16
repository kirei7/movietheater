package com.epam.rd.movietheater.service.user;

import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.IdentifiableEntityService;

import java.util.Optional;

public interface UserService extends IdentifiableEntityService<User> {
    Optional<User> getUserByEmail(String email);
}
