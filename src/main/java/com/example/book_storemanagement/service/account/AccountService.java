package com.example.book_storemanagement.service.account;

import com.example.book_storemanagement.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;
}
