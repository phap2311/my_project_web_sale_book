package com.example.book_storemanagement.service.account;

import com.example.book_storemanagement.model.entity.Account;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    List<Account> findAllUser();

    Account saveUser(Account account);
    void updateUser(Long accountId);
    void updateSeller(Long accountId);


    Optional<Account> findById(Long id);

    void delete(Long id);

    List<Account> checkUserName(String userName);

}
