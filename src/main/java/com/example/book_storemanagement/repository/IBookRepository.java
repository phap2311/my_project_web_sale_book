package com.example.book_storemanagement.repository;
import com.example.book_storemanagement.model.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Books, Long> {

}
