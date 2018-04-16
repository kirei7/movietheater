package com.epam.rd.movietheater.model.factory;

import com.epam.rd.movietheater.model.entity.User;

import java.time.LocalDate;

public abstract class UserFactory {
    public static User create(String firstName, String lastName, String email, LocalDate birthday) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setBirthday(birthday);
        return user;
    }
}
