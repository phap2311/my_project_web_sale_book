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

//    @Transactional
//    @Modifying
//    @Query(nativeQuery = true, value = "INSERT INTO cart (account_id, quantity, date_purchase, total_price, status,bill_id,books_id) SELECT :accountId, :#{#c.quantity}, current_time(), b.price, 'pending',:#{#c.bill_id} , :bookId\n" +
//            "FROM books b\n" +
//            "JOIN cart c ON b.id = c.books_id \n" +
//            "JOIN account acc ON acc.id = c.account_id \n" +
//            "WHERE b.id = :bookId and acc.id = :accountId ")
//    void createCart(@Param("accountId") Long accountId, @Param("bookId")Long bookId, @Param("quantity") int quantity, @Param("cart")Cart cart);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO cart (account_id, quantity, date_purchase, total_price, status, bill_id, books_id) " +
            "VALUES (:accountId, :#{#cart.quantity}, current_time(), " +
            "(SELECT b.price FROM Books b WHERE b.id = :bookId), 'pending',null, :bookId)")
    void createCart(@Param("accountId") Long accountId,
                    @Param("bookId") Long bookId,
                    @Param("cart") Cart cart);

    @Query(nativeQuery = true, value = "select b.name,b.image, b.author,c.id, c.quantity, c.date_purchase, c.total_price, c.books_id as bookId, c.bill_id as billId, c.account_id as accountId \n" +
            " from cart c join books b on c.books_id = b.id\n" +
            " where c.account_id = :accountId")
    List<CartDTO> findAllCart(@Param("accountId") Long accountId);

    @Query(nativeQuery = true, value = " select sum(quantity) as totalQuantity ,sum(total_price) as totalMoney\n" +
            " from cart \n" +
            " where account_id = :accountId\n" +
            " group by account_id")
    Optional<TotalPriceDTO> getTotal(Long accountId);


}

