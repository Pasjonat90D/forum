package com.myproject.forum.controller;

import com.myproject.forum.service.CategoriesService;
import com.myproject.forum.service.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DefaultController {

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("categories", categoriesService.getAllCategories());
        model.addAttribute("topics", topicsService.getAllTopics());
        return "/home";
    }
}
