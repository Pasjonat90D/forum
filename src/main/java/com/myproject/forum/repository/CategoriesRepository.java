package com.myproject.forum.repository;

import com.myproject.forum.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, String>  {

    Category findByName(String name);
}
