package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
      Category savedCategory=  this.categoryService.addCategory(category);
      return  ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category updatedCategory=this.categoryService.updateCategory(category);
        return  ResponseEntity.ok(updatedCategory);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<Category> getCategory(@PathVariable("catId") Long catId){
        Category category=this.categoryService.getCategory(catId);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/")
    public ResponseEntity<Set<Category>> getCategories(){
        Set<Category> categories=this.categoryService.getCatgories();
        return  ResponseEntity.ok(categories);
    }

    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable("catId") Long catId){
        this.categoryService.deleteCategory(catId);
    }


}
