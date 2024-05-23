package com.example.book_storemanagement.service.cart;

import com.example.book_storemanagement.model.dto.CartDTO;
import com.example.book_storemanagement.model.entity.Cart;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartService {
    void addBookToCart(Long bookId, Long accountId,int quantity);

    List<CartDTO> findAllCart(Long accountId );

}
