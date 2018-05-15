package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.model.entity.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class FrontController {

    @RequestMapping
    public String indexPage() {
        return "redirect:/events";
    }

    @RequestMapping("login")
    public ModelAndView login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("forward:/events");
        }
        return new ModelAndView("login");
    }

    @RequestMapping("test")
    @ResponseBody
    public User test() {
        return new User("nick", "pass");
    }
}
