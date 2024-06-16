package com.example.book_storemanagement.repository;
import com.example.book_storemanagement.model.entity.Books;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBookRepository extends JpaRepository<Books, Long> {
    @Query(nativeQuery = true, value = "select * from books b where b.account_id = :accountId")
    List<Books> findAllBookByAccountId(@Param("accountId") Long accountId);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE books b\n" +
            "JOIN cart c ON b.id = c.books_id\n" +
            "SET b.quantity = b.quantity - c.quantity\n" +
            "where c.account_id = :idAccount")
    void updateQuantityBook(@Param("idAccount")Long idAccount);


}
