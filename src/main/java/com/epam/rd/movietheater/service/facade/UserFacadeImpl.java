package com.epam.rd.movietheater.service.facade;

import com.epam.rd.movietheater.exception.UserNotFoundException;
import com.epam.rd.movietheater.exception.UserRegistrationException;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.model.entity.UserAccount;
import com.epam.rd.movietheater.security.UserRole;
import com.epam.rd.movietheater.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UserFacadeImpl implements UserFacade {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserFacadeImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User registerUser(User user) {
        checkUserEntity(user);
        UserAccount account = new UserAccount();
        account.setUser(user);
        user.setAccount(account);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(UserRole.REGISTERED_USER);
        return userService.save(user);
    }

    private void checkUserEntity(User user) {
        user.setNickName(user.getNickName().trim());
        user.setPassword(user.getPassword().trim());
        if (StringUtils.isEmpty(user.getNickName()))
            throw new UserRegistrationException("Nickname cannot be empty");
        if (StringUtils.isEmpty(user.getPassword()))
            throw new UserRegistrationException("Password cannot be empty");

        try {
            if (userService.getUserByNickName(user.getNickName()) != null)
                throw new UserRegistrationException("User with given nickname already registered");
        } catch (UserNotFoundException ex) {
        }

    }
}
