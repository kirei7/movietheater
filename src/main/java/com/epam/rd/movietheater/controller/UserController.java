package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.exception.AccessDeniedException;
import com.epam.rd.movietheater.exception.UserNotFoundException;
import com.epam.rd.movietheater.model.dto.UserDto;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.facade.UserFacade;
import com.epam.rd.movietheater.service.user.UserService;
import com.epam.rd.movietheater.util.mapper.UserDtoMapper;
import com.epam.rd.movietheater.util.userprovider.UserProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserFacade userFacade;
    private UserProvider userProvider;
    private UserDtoMapper userDtoMapper;
    private ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService,
                          UserFacade userFacade,
                          UserProvider userProvider,
                          UserDtoMapper userDtoMapper,
                          @Value("#{ObjectMapperProvider.getObjectMapper()}") ObjectMapper objectMapper) {
        this.userService = userService;
        this.userFacade = userFacade;
        this.userProvider = userProvider;
        this.userDtoMapper = userDtoMapper;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable Long userId, Model model) {
        User target = userService.getById(userId).orElseThrow(UserNotFoundException::new);
        User logged = userProvider.getCurrentUser();
        if (!target.getNickName().equals(logged.getNickName()))
            throw new AccessDeniedException();
        model.addAttribute("user", userDtoMapper.toDto(target));
        return "user";
    }

    @PostMapping("/update")
    public String updateUser(UserDto user, Model model) throws IOException {
        User target = userProvider.getCurrentUser();
        target.setFirstName(user.getFirstName());
        target.setLastName(user.getLastName());
        target.setEmail(user.getEmail());
        userService.save(target);
        model.addAttribute("user", target);
        return "user";
    }


    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        return "register";
    }

    @PostMapping
    public String registerUser(UserDto user, HttpServletRequest request) {
        String password = user.getPassword();
        User registered = userFacade.registerUser(user);
        try {
            request.login(registered.getNickName(), password);
        } catch (ServletException e) {
            throw new RuntimeException("Error while logging");
        }
        return "redirect:/users/" + registered.getId();
    }
}
