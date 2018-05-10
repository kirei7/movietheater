package com.epam.rd.movietheater.util.mapper;

import com.epam.rd.movietheater.model.dto.UserDto;
import com.epam.rd.movietheater.model.entity.User;

public class UserDtoMapper implements EntityMapper<User, UserDto> {

    @Override
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setNickName(userDto.getNickName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setBirthday(userDto.getBirthday());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
