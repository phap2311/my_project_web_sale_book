package com.example.book_storemanagement.repository;

import com.example.book_storemanagement.model.dto.SellerDetail;
import com.example.book_storemanagement.model.entity.Account;
import com.example.book_storemanagement.model.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ISellerRepository extends JpaRepository<Account, Long> {
    @Query(nativeQuery = true, value = "SELECT \n" +
            "    a.name ,\n" +
            "    a.phone,\n" +
            "    a.email,\n" +
            "    a.avatar,\n" +
            "    a.username,\n" +
            "    COUNT(b.id) AS numberOfBook,\n" +
            "    IFNULL(SUM(c.total_price), 0) AS totalRevenue\n" +
            "FROM \n" +
            "    account a\n" +
            "LEFT JOIN \n" +
            "    books b ON a.id = b.account_id\n" +
            "LEFT JOIN \n" +
            "    cart c ON b.id = c.books_id\n" +
            "LEFT JOIN\n" +
            "\taccount_roles ar on ar.account_id = a.id\n" +
            "WHERE ar.roles_id = 3\n" +
            "GROUP BY \n" +
            "    a.id, a.name, a.phone, a.email, a.avatar, a.username;")
    List<SellerDetail> getAllSeller();

    @Query(nativeQuery = true, value = " SELECT \n" +
            "    a.id,\n" +
            "    a.name ,\n" +
            "    a.phone,\n" +
            "    a.email,\n" +
            "    a.avatar,\n" +
            "    a.username,\n" +
            "    a.address,\n" +
            "    COUNT(b.id) AS numberOfBook,\n" +
            "    IFNULL(SUM(c.total_price), 0) AS totalRevenue\n" +
            "FROM \n" +
            "    account a\n" +
            "LEFT JOIN \n" +
            "    books b ON a.id = b.account_id\n" +
            "LEFT JOIN \n" +
            "    cart c ON b.id = c.books_id\n" +
            "LEFT JOIN\n" +
            "\taccount_roles ar on ar.account_id = a.id\n" +
            "WHERE ar.roles_id = 3 and a.id = :id\n" +
            "GROUP BY \n" +
            "    a.id, a.name, a.phone, a.email, a.avatar, a.username, a.address;")
    Optional<SellerDetail> getSellerDetail(@Param("id") Long id);


}
