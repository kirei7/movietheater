package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.exception.UserNotFoundException;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.facade.UserFacade;
import com.epam.rd.movietheater.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserFacade userFacade;

    @Autowired
    public UserController(UserService userService, UserFacade userFacade) {
        this.userService = userService;
        this.userFacade = userFacade;
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.getById(userId).orElseThrow(UserNotFoundException::new));
        return "user";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        return "register";
    }

    @PostMapping
    public String registerUser(User user, HttpServletRequest request) {
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
