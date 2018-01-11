package com.myproject.forum.controller.admin;

import com.myproject.forum.models.User;
import com.myproject.forum.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class UsersController {


    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/admin/users" , method = RequestMethod.GET)
    public String getAllUsers(Model model){
        model.addAttribute("users", usersService.getAllUsers());
        return "admin/users";
    }

    @RequestMapping(value = "/admin/{id}" , method = RequestMethod.GET)
    public String getUser(Model model,  @PathVariable("id") long id){
        model.addAttribute("user", usersService.getUserById(id) );
        return "admin/user";
    }

    @RequestMapping(value = "/admin/{id}" , method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") long id,Principal principal){
        usersService.deleteUser(id,principal.getName());
        return "redirect:admin/users";
    }
    @RequestMapping(value = "/admin/user/{id}" , method = RequestMethod.PUT)
    public String changeToAdmin( @PathVariable("id") long id, User user, Principal principal){
        usersService.exchangeUserForAdmin(id,principal.getName());
        return "redirect:/admin/users";
    }

}
