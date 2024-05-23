package com.example.book_storemanagement.service.cart;

import com.example.book_storemanagement.model.entity.Cart;
import org.springframework.data.repository.query.Param;

public interface ICartService {
    void addBookToCart(Long bookId, Long accountId,int quantity);


}
