package com.example.book_storemanagement.controller;

import com.example.book_storemanagement.model.dto.CartDTO;
import com.example.book_storemanagement.service.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/cart/")
public class CartController {
    @Autowired
    private ICartService iCartService;

    @PostMapping("create")
    public ResponseEntity<Void> createCart(@RequestParam Long accountId , @RequestParam Long bookId,@RequestParam int quantity) {
        iCartService.addBookToCart (accountId, bookId,quantity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("{accountId}")
    public ResponseEntity<List<CartDTO>>findAllCart(@PathVariable Long accountId){
       List<CartDTO> carts = iCartService.findAllCart(accountId);
        return new ResponseEntity<>(carts,HttpStatus.OK);
    }

}
