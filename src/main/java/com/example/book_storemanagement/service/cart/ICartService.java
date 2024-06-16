package com.example.book_storemanagement.service.cart;

import com.example.book_storemanagement.model.dto.CartDTO;
import com.example.book_storemanagement.model.dto.TotalPriceDTO;
import com.example.book_storemanagement.model.entity.Cart;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    // void createCart( Long accountId, Long bookId,  int quantity, Cart cart);
    void createCart(Long accountId, Long bookId, Cart cart);


    List<CartDTO> findAllCart(Long accountId);
    Optional<CartDTO>getAllCartByBook(Long accountId, Long bookId);

    Optional<TotalPriceDTO> getTotal(Long accountId);

    void removeBookToCart(Long id);


    void updateCartWithBill(Long accountId, String billCode);

    void removeAllBookCart(Long accountId);

}
