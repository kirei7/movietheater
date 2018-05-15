package com.epam.rd.movietheater.util.mapper;

import com.epam.rd.movietheater.model.dto.UserDto;
import com.epam.rd.movietheater.model.entity.User;
import org.springframework.stereotype.Component;

@Component
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

    @Override
    public UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setNickName(entity.getNickName());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setBirthday(entity.getBirthday());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}
