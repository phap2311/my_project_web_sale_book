package com.example.book_storemanagement.repository;

import com.example.book_storemanagement.model.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface IBookRepository extends JpaRepository<Books, Long> {

}
