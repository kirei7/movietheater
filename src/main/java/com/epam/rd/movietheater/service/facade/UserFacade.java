package com.epam.rd.movietheater.service.facade;

import com.epam.rd.movietheater.model.dto.UserDto;
import com.epam.rd.movietheater.model.entity.User;

public interface UserFacade {
    User registerUser(UserDto userDto);
}
