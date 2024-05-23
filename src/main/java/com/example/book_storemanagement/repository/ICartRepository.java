package com.example.book_storemanagement.repository;

import com.example.book_storemanagement.model.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO books_carts (books_id, carts_id) VALUES (:bookId, :cartId)")
    void addBookToCart(@Param("bookId") Long bookId, @Param("cartId") Long cartId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO cart (account_id, quantity, date_purchase, price, status) VALUES (:accountId, :quantity, current_time(), 0.0, 'pending')")
    void createCart(@Param("accountId") Long accountId,@Param("quantity") int quantity);

    @Query(nativeQuery = true, value = "SELECT LAST_INSERT_ID()")
    Long getLastInsertedCartId();
}

