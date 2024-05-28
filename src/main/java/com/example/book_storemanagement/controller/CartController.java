package com.example.book_storemanagement.controller;

import com.example.book_storemanagement.model.dto.CartDTO;
import com.example.book_storemanagement.model.dto.TotalPriceDTO;
import com.example.book_storemanagement.model.entity.Cart;
import com.example.book_storemanagement.service.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/cart/")
public class CartController {
    @Autowired
    private ICartService iCartService;

    @PostMapping("create")
    public ResponseEntity<Void> createCart(@RequestParam Long accountId, @RequestParam Long bookId, @RequestParam int quantity) {
        iCartService.createCart(accountId, bookId, quantity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{accountId}")
    public ResponseEntity<List<CartDTO>> findAllCart(@PathVariable Long accountId) {
        List<CartDTO> carts = iCartService.findAllCart(accountId);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("total/{accountId}")
    public ResponseEntity<TotalPriceDTO> findTotalMoney(@PathVariable Long accountId) {
        Optional<TotalPriceDTO> totalPriceDTO = iCartService.getTotal(accountId);
        if (!totalPriceDTO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(totalPriceDTO.get(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeBooksToCart(@PathVariable Long id ) {
        iCartService.removeBookToCart(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
