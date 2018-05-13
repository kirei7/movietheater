package com.epam.rd.movietheater.service.facade;

import com.epam.rd.movietheater.model.dto.UserDto;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.security.UserRole;

public interface UserFacade {
    User registerUser(UserDto userDto);

    User registerUser(UserDto userDto, UserRole... roles);
}
