package com.epam.rd.movietheater.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FrontController {

    @RequestMapping
    public String indexPage() {
        return "index";
    }

}
