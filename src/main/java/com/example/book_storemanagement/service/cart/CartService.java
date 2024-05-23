package com.example.book_storemanagement.service.cart;

import com.example.book_storemanagement.model.dto.CartDTO;
import com.example.book_storemanagement.model.entity.Account;
import com.example.book_storemanagement.model.entity.Books;
import com.example.book_storemanagement.model.entity.Cart;
import com.example.book_storemanagement.repository.IBookRepository;
import com.example.book_storemanagement.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository iCartRepository;


    @Override
    public void addBookToCart(Long accountId, Long bookId, int quantity) {
        iCartRepository.createCart(accountId,quantity);
        Long cartId = iCartRepository.getLastInsertedCartId();
        iCartRepository.addBookToCart(bookId, cartId);
    }

    @Override
    public List<CartDTO> findAllCart(Long accountId) {
        return iCartRepository.findAllCart(accountId);
    }
}
