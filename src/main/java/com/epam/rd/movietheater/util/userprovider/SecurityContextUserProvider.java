package com.epam.rd.movietheater.util.userprovider;

import com.epam.rd.movietheater.config.security.UserDetailsServiceImpl;
import com.epam.rd.movietheater.model.entity.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextUserProvider implements UserProvider {
    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication instanceof AnonymousAuthenticationToken) ? new User() : ((UserDetailsServiceImpl.CurrentUser) authentication.getPrincipal()).getUser();
    }
}