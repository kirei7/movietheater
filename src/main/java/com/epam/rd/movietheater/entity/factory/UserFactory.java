package com.epam.rd.movietheater.entity.factory;

import com.epam.rd.movietheater.entity.User;

public abstract class UserFactory {
    public static User create(String firstName, String lastName, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return user;
    }
}
