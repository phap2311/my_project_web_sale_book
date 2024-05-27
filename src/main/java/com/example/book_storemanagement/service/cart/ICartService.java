package com.example.book_storemanagement.service.cart;

import com.example.book_storemanagement.model.dto.CartDTO;
import com.example.book_storemanagement.model.dto.TotalPriceDTO;
import com.example.book_storemanagement.model.entity.Cart;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    void addBookToCart(Long bookId, Long accountId,int quantity);

    List<CartDTO> findAllCart(Long accountId );
    Optional<TotalPriceDTO>getTotal(Long accountId);
    void removeBookToCart(Long bookId,Long cartId);


}
