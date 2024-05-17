package com.example.book_storemanagement.service.book;

import com.example.book_storemanagement.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService{
    @Autowired
    private IBookRepository iBookRepository;
}
