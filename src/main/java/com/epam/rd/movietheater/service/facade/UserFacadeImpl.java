package com.epam.rd.movietheater.service.facade;

import com.epam.rd.movietheater.exception.UserNotFoundException;
import com.epam.rd.movietheater.exception.UserRegistrationException;
import com.epam.rd.movietheater.model.dto.UserDto;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.model.entity.UserAccount;
import com.epam.rd.movietheater.security.UserRole;
import com.epam.rd.movietheater.service.user.UserService;
import com.epam.rd.movietheater.util.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Service
public class UserFacadeImpl implements UserFacade {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private UserDtoMapper dtoMapper;

    @Autowired
    public UserFacadeImpl(UserService userService, PasswordEncoder passwordEncoder, UserDtoMapper dtoMapper) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public User registerUser(UserDto userDto) {
        return registerUser(userDto, UserRole.REGISTERED_USER);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User registerUser(UserDto userDto, UserRole... roles) {
        checkUserDto(userDto);
        UserAccount account = new UserAccount();
        User user = dtoMapper.toEntity(userDto);
        account.setUser(user);
        user.setAccount(account);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.addRole(UserRole.REGISTERED_USER);
        Arrays.stream(roles).forEach(user::addRole);
        return userService.save(user);
    }

    private void checkUserDto(UserDto user) {
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
