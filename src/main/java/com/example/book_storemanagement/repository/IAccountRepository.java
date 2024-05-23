package com.example.book_storemanagement.repository;

import com.example.book_storemanagement.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Long> {
}
