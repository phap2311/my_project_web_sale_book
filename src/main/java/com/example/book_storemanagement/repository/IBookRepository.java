package com.example.book_storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface IBookRepository extends JpaRepository<Book, Long> {

}
