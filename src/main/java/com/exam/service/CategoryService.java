package com.exam.service;

import com.exam.model.exam.Category;

import java.util.Set;

public interface CategoryService {
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public Set<Category> getCatgories();
    public Category getCategory(Long cid);
    public void deleteCategory(Long cid);
}
