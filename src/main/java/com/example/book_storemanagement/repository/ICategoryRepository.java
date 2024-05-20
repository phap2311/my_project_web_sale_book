package com.example.book_storemanagement.repository;

import com.example.book_storemanagement.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Long> {

}
