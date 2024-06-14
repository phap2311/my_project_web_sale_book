package com.example.book_storemanagement.repository;

import com.example.book_storemanagement.model.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String name);

    @Query(nativeQuery = true, value = "select a.username, a.avatar, a.name, a.phone, a.email, a.address from account a\n" +
            "join account_roles ar on a.id = ar.account_id\n" +
            "where ar.roles_id = 1;")
    List<Account> findAllUser();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into account_roles (account_id,roles_id) values(:accountId, 1) ")
    void updateUser(@Param("accountId") Long accountId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into account_roles (account_id,roles_id) values(:accountId, 3) ")
    void updateSeller(@Param("accountId") Long accountId);

    @Query(nativeQuery = true,value = "select * from account where account.username = :userName")
    List<Account> checkUserName(@Param("userName") String userName);
}
