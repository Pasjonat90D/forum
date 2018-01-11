package com.myproject.forum.controller.admin;

import com.myproject.forum.models.Category;
import com.myproject.forum.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    public String getCategories(Model model) {
        model.addAttribute("categories", categoriesService.getAllCategories());
        model.addAttribute("category", categoriesService.getNewCategory());
        return "admin/categories";
    }
    @RequestMapping(value = "/admin/categories{name}", method = RequestMethod.DELETE)
    public String deleteCategory( @RequestParam("name") String name) {
        categoriesService.deleteCategory(name);
        return "redirect:/admin/categories";
    }
    @RequestMapping(value = "/admin/editCategor{name}", method = RequestMethod.GET)
    public String editCategory(@PathVariable("name") String name, Model model) {
        model.addAttribute("category", categoriesService.getCategoryByName(name));
        return "/admin/editCategor";
    }
    @RequestMapping(value = "/admin/editCategor", method = RequestMethod.PUT)
    public String editCategory(Category category,@RequestParam("oldName") String name) {
        categoriesService.editCategory(category,name);
        return "redirect:/admin/categories";
    }
    @RequestMapping(value = "/admin/addCategory", method = RequestMethod.GET)
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "/admin/addCategory";
    }
    @RequestMapping(value = "/admin/addCategory", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute Category category){
        categoriesService.saveCategory(category);
        return "redirect:/admin/categories";

    }
}
