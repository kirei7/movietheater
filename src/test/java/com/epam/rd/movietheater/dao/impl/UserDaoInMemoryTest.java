package com.epam.rd.movietheater.dao.impl;

import com.epam.rd.movietheater.dao.UserDao;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.model.entity.factory.UserFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoInMemoryTest {

    private UserDao userDao = new InMemoryUserDao();

    @Test
    public void save() {
        User user = getUser();
        userDao.save(user);
        assertEquals(user.getFirstName(), userDao.getById(1L).orElse(new User()).getFirstName());

        user.setId(4L);
        userDao.save(user);
        assertEquals(user.getFirstName(), userDao.getById(4L).orElse(new User()).getFirstName());
    }

    @Test
    public void remove() {
        User user = getUser();
        userDao.save(user);
        assertEquals(user.getFirstName(), userDao.getById(1L).orElse(new User()).getFirstName());

        userDao.remove(user);
        assertFalse(userDao.getById(1L).isPresent());
    }


    @Test
    public void getByEmail() {
        User user1 = getUser();
        User user2 = getUser();

        String email = "another@gmail.com";
        user2.setEmail(email);

        userDao.save(user1);
        userDao.save(user2);

        assertEquals(user2, userDao.getByEmail(email).orElse(new User()));
    }

    @Test
    public void findAll() {
        userDao.save(getUser());
        userDao.save(getUser());
        userDao.save(getUser());
        userDao.save(getUser());

        assertEquals(4, userDao.findAll().size());
    }

    private User getUser() {
        return UserFactory.create("first", "last", "mail@mail.com");
    }
}