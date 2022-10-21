package com.exam.service.Impl;

import com.exam.model.exam.Category;
import com.exam.repository.CategoryRepository;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
       return  this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
       return this.categoryRepository.save(category);
    }

    @Override
    public Set<Category> getCatgories() {
        return new HashSet<>(this.categoryRepository.findAll());
    }

    @Override
    public Category getCategory(Long cid) {
        return this.categoryRepository.findById(cid).get();
    }

    @Override
    public void deleteCategory(Long cid) {
         this.categoryRepository.deleteById(cid);
    }
}
