package com.example.book_storemanagement.controller;

import com.example.book_storemanagement.model.entity.Category;
import com.example.book_storemanagement.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = iCategoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
