package com.example.book_storemanagement.repository;

import com.example.book_storemanagement.model.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;

public interface IBookRepository extends JpaRepository<Books, Long> {

}
