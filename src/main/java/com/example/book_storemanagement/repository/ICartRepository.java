package com.example.book_storemanagement.repository;

import com.example.book_storemanagement.model.dto.CartDTO;
import com.example.book_storemanagement.model.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO books_carts (books_id, carts_id) VALUES (:bookId, :cartId)")
    void addBookToCart(@Param("bookId") Long bookId, @Param("cartId") Long cartId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO cart (account_id, quantity, date_purchase, total_price, status) VALUES (:accountId, :quantity, current_time(), 0.0, 'pending')")
    void createCart(@Param("accountId") Long accountId, @Param("quantity") int quantity);

    @Query(nativeQuery = true, value = "SELECT LAST_INSERT_ID()")
    Long getLastInsertedCartId();
@Query(nativeQuery = true, value = "select b.name, c.quantity, c.date_purchase, c.total_price \n" +
        " from cart c join books_carts bc on bc.carts_id = c.id\n" +
        " join books b on bc.books_id = b.id\n" +
        " where c.account_id = :accountId")
    List<CartDTO> findAllCart(@Param("accountId") Long accountId );
}

