package com.epam.rd.movietheater.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
public class HelloController {

    @GetMapping
    public String sayHello() {
        return "hello";
    }

    @RequestMapping(value = "file", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getName());
        return "uploaded";
    }
}
