package com.example.book_storemanagement.repository;

import com.example.book_storemanagement.model.dto.CartDTO;
import com.example.book_storemanagement.model.dto.TotalPriceDTO;
import com.example.book_storemanagement.model.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

    @Query(nativeQuery = true, value = "select b.name,b.image, b.author, c.quantity, c.date_purchase, c.total_price, bc.books_id as bookId, bc.carts_id as cartId \n" +
            " from cart c join books_carts bc on bc.carts_id = c.id\n" +
            " join books b on bc.books_id = b.id\n" +
            " where c.account_id = :accountId")
    List<CartDTO> findAllCart(@Param("accountId") Long accountId);

    @Query(nativeQuery = true, value = " select sum(quantity) as totalQuantity ,sum(total_price) as totalMoney\n" +
            " from cart \n" +
            " where account_id = :accountId\n" +
            " group by account_id")
    Optional<TotalPriceDTO> getTotal(Long accountId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from books_carts where books_id = :bookId and carts_id = :cartId")
    void removeBookToCart(@Param("bookId") Long bookId, @Param("cartId") Long cartId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM cart WHERE id = :carts_id")
    void deleteCartIfEmpty(@Param("carts_id") Long carts_id);
}

