package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.stats.StatsService;
import com.epam.rd.movietheater.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stats")
public class StatsController {

    private StatsService statsService;
    private UserService userService;

    @Autowired
    public StatsController(StatsService statsService, UserService userService) {
        this.statsService = statsService;
        this.userService = userService;
    }

    @GetMapping("/discounts")
    public String getTotalStats(Model model) {
        model.addAttribute("discounts", statsService.getDiscountCounts());
        return "stats/discount";
    }

    @GetMapping("/{nickName}/discounts")
    public String getUserStats(@PathVariable String nickName, Model model) {
        User user = userService.getUserByNickName(nickName);
        model.addAttribute("user", user);
        model.addAttribute("discounts", statsService.getDiscountCounts(user));
        return "stats/discountUser";
    }

}
