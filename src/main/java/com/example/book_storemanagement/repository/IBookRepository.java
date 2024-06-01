package com.example.book_storemanagement.repository;
import com.example.book_storemanagement.model.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBookRepository extends JpaRepository<Books, Long> {
    @Query(nativeQuery = true, value = "select * from books b where b.account_id = :accountId")
    List<Books> findAllBookByAccountId(@Param("accountId") Long accountId);
}
