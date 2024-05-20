package com.example.book_storemanagement.service.category;

import com.example.book_storemanagement.model.entity.Category;
import com.example.book_storemanagement.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Override
    public List<Category> findAll() {
        return iCategoryRepository.findAll();
    }
}
