package com.myproject.forum.service;

import com.myproject.forum.models.Category;

import java.util.List;

public interface CategoriesService {

    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category getNewCategory();
    void deleteCategory(String name);
    void editCategory(Category editedCategory, String oldNameCategory);
    void saveCategory(Category newCategory);
}
