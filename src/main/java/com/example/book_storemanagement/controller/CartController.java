package com.example.book_storemanagement.controller;

import com.example.book_storemanagement.config.service.JwtService;
import com.example.book_storemanagement.model.dto.CartDTO;
import com.example.book_storemanagement.model.dto.CartDTOI1;
import com.example.book_storemanagement.model.dto.TotalPriceDTO;
import com.example.book_storemanagement.model.entity.Cart;
import com.example.book_storemanagement.repository.IAccountRepository;
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

    @Autowired
    private JwtService jwtService;
    @Autowired
    private IAccountRepository iAccountRepository;

    @PostMapping("create")
    public ResponseEntity<Cart> createCart(@RequestParam Long accountId, @RequestParam Long bookId, @RequestBody Cart cart, @RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.substring(7);
        String account = jwtService.getUsernameFromJwtToken(token);
        cart.setAccount(iAccountRepository.findByUsername(account));
        iCartService.createCart(accountId, bookId, cart);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{accountId}")
    public ResponseEntity<List<CartDTOI1>> findAllCart(@PathVariable Long accountId) {
        List<CartDTOI1> carts = iCartService.findAllCart(accountId);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

//    @GetMapping("{accountId}")
//    public ResponseEntity<List<CartDTO>> findAllCart(@PathVariable Long accountId) {
//        List<CartDTO> carts = iCartService.findAllCart(accountId);
//        return new ResponseEntity<>(carts, HttpStatus.OK);
//    }
    @GetMapping("total/{accountId}")
    public ResponseEntity<TotalPriceDTO> findTotalMoney(@PathVariable Long accountId) {
        Optional<TotalPriceDTO> totalPriceDTO = iCartService.getTotal(accountId);
        if (!totalPriceDTO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(totalPriceDTO.get(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeBooksToCart(@PathVariable Long id) {
        iCartService.removeBookToCart(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    @DeleteMapping("/{accountId}/book/{bookId}")
//    public ResponseEntity<List<CartDTOI1>> removeBookFromCart(@PathVariable Long accountId, @PathVariable Long bookId) {
//        List<CartDTOI1> updatedCart = iCartService.removeBookFromCart(accountId, bookId);
//        return ResponseEntity.ok(updatedCart);
//    }

//    @DeleteMapping("/{accountId}")
//    public ResponseEntity<List<CartDTO>> removeAllBooksFromCart(@PathVariable Long accountId) {
//        List<CartDTO> updatedCart = iCartService.removeAllBooksFromCart(accountId);
//        return ResponseEntity.ok(updatedCart);
//    }

    @GetMapping("{accountId}/book/{bookId}")
    public ResponseEntity<CartDTOI1> getAllCartByBooks(@PathVariable Long accountId, @PathVariable Long bookId) {
        Optional<CartDTOI1> cartDTOOptional = iCartService.getAllCartByBook(accountId, bookId);
        if (!cartDTOOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(cartDTOOptional.get(), HttpStatus.OK);
    }


    @GetMapping("{billId}/account/{accountId}")
    public ResponseEntity<List<CartDTOI1>> getAllCartByBill(@PathVariable Long accountId, @PathVariable Long billId) {
        List<CartDTOI1> cart = iCartService.getAllCartByBill(accountId, billId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
