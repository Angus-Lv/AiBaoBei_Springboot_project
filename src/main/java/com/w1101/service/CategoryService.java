package com.w1101.service;

import com.w1101.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();

    boolean addCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(Integer id);
}
