package com.xxxx.springsecuritydemo.service;

import com.xxxx.springsecuritydemo.entity.Category;

import java.util.List;

public interface ICategoryService {

    public Category saveCategory(Category category);

    public Category getCategory(Integer categoryId);

    public List<Category> getAll();

    public void deleteCategory(Integer CategoryId);

}
