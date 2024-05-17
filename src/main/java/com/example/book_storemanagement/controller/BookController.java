package com.example.book_storemanagement.controller;

import com.example.book_storemanagement.model.entity.Books;
import com.example.book_storemanagement.service.book.IBookService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private IBookService iBookService;
    @GetMapping()
    public ResponseEntity<List<Books>>findAllBook(){
        List<Books>booksList = iBookService.findAll();
        return new ResponseEntity<>(booksList, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Books>getDetailBook(@PathVariable Long id){
        Optional<Books>booksOptional = iBookService.findById(id);
        if(!booksOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(booksOptional.get(),HttpStatus.OK);
    }

}
