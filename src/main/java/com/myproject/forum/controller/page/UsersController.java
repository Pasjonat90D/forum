package com.myproject.forum.controller.page;


import com.myproject.forum.models.User;
import com.myproject.forum.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller("pageUsersController")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/page/user", method = RequestMethod.GET)
    public String getUser(Principal principal, Model model){
        String name = principal.getName();
        model.addAttribute("user", usersService.getUserByEmail(name));
        return "page/user";
    }

    @RequestMapping(value = "/page/editUser{id}", method = RequestMethod.GET)
    public String editCategory(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "/page/editUser";
    }

    @RequestMapping(value = "/page/editUser", method = RequestMethod.PUT)
    public String updateUser(User user) {
        usersService.updateUser(user);
        return "redirect:/page/user";
    }
    @RequestMapping(value = "/page/user", method = RequestMethod.DELETE)
    public String deleteUser( @RequestParam("id") long id, Principal principal) {
        SecurityContextHolder.clearContext();
        usersService.deleteUser(id, principal.getName());
        return "redirect:home";
    }
}
