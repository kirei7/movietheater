package com.epam.rd.movietheater.util.userprovider;

import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class DummyUserProvider implements UserProvider {

    private UserService userService;
    private User dummyUser;

    @Autowired
    public DummyUserProvider(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void createDummyUser() {
        dummyUser = new User("John", "Doe", "jdoe@mail.com", LocalDate.now().minusYears(21));
        userService.save(dummyUser);
    }

    @Override
    public User getCurrentUser() {
        return dummyUser;
    }

}
