package com.example.book_storemanagement.controller;

import com.example.book_storemanagement.model.entity.Books;
import com.example.book_storemanagement.model.entity.Category;
import com.example.book_storemanagement.service.book.IBookService;
import com.example.book_storemanagement.service.category.ICategoryService;
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
    @Autowired
    private ICategoryService iCategoryService;

    @ModelAttribute("/category")
    public List<Category> findAllCategory() {
        return iCategoryService.findAll();
    }

    @GetMapping()
    public ResponseEntity<List<Books>> findAllBook() {
        List<Books> booksList = iBookService.findAll();
        return new ResponseEntity<>(booksList, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Books> getDetailBook(@PathVariable Long id) {
        Optional<Books> booksOptional = iBookService.findById(id);
        if (!booksOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(booksOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/createBook")
    public ResponseEntity<Books> addBook(@RequestBody Books books) {
        Books books1 = iBookService.save(books);
        return new ResponseEntity<>(books1, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Books> updateBooks(@RequestBody Books books, @PathVariable Long id) {
        Optional<Books> bookOptional = iBookService.findById(id);
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        books.setId(bookOptional.get().getId());
        Books updateBook = iBookService.save(books);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Books> deleteBook(@PathVariable Long id) {
        Optional<Books> booksOptional = iBookService.findById(id);
        if (!booksOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iBookService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
