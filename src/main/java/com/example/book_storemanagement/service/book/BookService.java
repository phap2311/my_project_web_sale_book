package com.example.book_storemanagement.service.book;

import com.example.book_storemanagement.model.entity.Books;
import com.example.book_storemanagement.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
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

    @Override
    public Books save(Books books) {
        return iBookRepository.save(books);
    }

    @Override
    public void delete(Long id) {
        iBookRepository.deleteById(id);
    }

    @Override
    public List<Books> findAllBookByAccountId(Long accountId) {
        return iBookRepository.findAllBookByAccountId(accountId);
    }

    @Override
    public void updateQuantityBook(Long idAccount) {
        iBookRepository.updateQuantityBook(idAccount);
    }

    @Override
    public List<Books> findAllBookByName(String name) {
        return iBookRepository.findAllBookByName(name);
    }
}
