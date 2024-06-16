package com.example.book_storemanagement.service.book;

import com.example.book_storemanagement.model.entity.Books;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Books> findAll();
    Optional<Books>findById(Long id);
    Books save(Books books);
    void delete(Long id);
    List<Books> findAllBookByAccountId(Long accountId);


    void updateQuantityBook(Long idAccount);


}
