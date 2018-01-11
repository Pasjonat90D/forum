package com.myproject.forum.service.impl;

import com.myproject.forum.models.Category;
import com.myproject.forum.models.Topic;
import com.myproject.forum.repository.CategoriesRepository;
import com.myproject.forum.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Category getCategoryByName(String name) {
        return categoriesRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public Category getNewCategory() {
        return new Category();
    }

    @Override
    public void deleteCategory(String name) {
        categoriesRepository.delete(name);
    }

    @Override
    public void editCategory(Category editedCategory, String oldNameCategory) {
        Category newCategory = new Category();
        newCategory.setName(editedCategory.getName());
        newCategory.setDescription(editedCategory.getDescription());
        Set<Topic> topics = new HashSet<>();
        categoriesRepository.findByName(oldNameCategory).getTopics().stream().forEach(System.out::println);
        topics.addAll(categoriesRepository.findByName(oldNameCategory).getTopics());
        categoriesRepository.save(newCategory);
        categoriesRepository.delete(oldNameCategory);
    }

    @Override
    public void saveCategory(Category newCategory) {
        categoriesRepository.save(newCategory);
    }
}
