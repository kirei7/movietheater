package com.epam.rd.movietheater.config.security;

import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String nickName) throws UsernameNotFoundException {
        User user = userService.findByNickName(nickName);
        if (user == null)
            throw new UsernameNotFoundException(String.format("User with nickname=%s was not found", nickName));
        return new CurrentUser(user);
    }
}