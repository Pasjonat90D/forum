package com.myproject.forum.controller.page;

import com.myproject.forum.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("pageCategoriesController")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(value = "/page/category/{name}", method = RequestMethod.GET)
    public String getCategory(@PathVariable("name") String name, Model model) {
        model.addAttribute("category", categoriesService.getCategoryByName(name));
        return "page/category";
    }
}
