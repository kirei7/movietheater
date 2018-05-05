package com.epam.rd.movietheater.config.security;

import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String nickName) throws UsernameNotFoundException {
        User user = userService
                .getUserByNickName(nickName);
        return new CurrentUser(user);
    }


    public class CurrentUser extends org.springframework.security.core.userdetails.User {
        private User user;

        public CurrentUser(User user) {
            super(
                    user.getNickName(),
                    user.getPassword(),
                    AuthorityUtils.createAuthorityList(user.getRoles().stream().map(Enum::toString).toArray(String[]::new)));
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }
}