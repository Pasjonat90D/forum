package com.myproject.forum.controller;

import com.myproject.forum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/entity/category", method = RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("categories",categoryRepository.findAll());
        return "entity/category";
    }

}
