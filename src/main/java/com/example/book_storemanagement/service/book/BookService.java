package com.example.book_storemanagement.service.book;

import com.example.book_storemanagement.model.entity.Books;
import com.example.book_storemanagement.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    private IBookRepository iBookRepository;

    @Override
    public List<Books> findAll() {
        return iBookRepository.findAll();
    }

    @Override
    public Optional<Books> findById(Long id) {
        return iBookRepository.findById(id);
    }

}
