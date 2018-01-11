package com.myproject.forum.controller;

import com.myproject.forum.models.User;
import com.myproject.forum.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreateAccountController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
    public String getAllCategories(Model model) {
        model.addAttribute("user", usersService.createNewUser());
        return "/createAccount";
    }


    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String createAccount(@ModelAttribute User user, Model model) {
        model.addAttribute("iscCreate" ,  usersService.saveUser(user));
        user.setPassword("");
        return "/createAccount";
    }

}
