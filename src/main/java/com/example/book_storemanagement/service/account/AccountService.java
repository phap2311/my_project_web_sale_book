package com.example.book_storemanagement.service.account;

import com.example.book_storemanagement.model.entity.Account;
import com.example.book_storemanagement.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;

    @Override
    public List<Account> findAllUser() {
        return iAccountRepository.findAllUser();
    }

    @Override
    public Account saveUser(Account account) {
        return iAccountRepository.save(account);
    }

    @Override
    public void updateUser(Long accountId) {
        iAccountRepository.updateUser(accountId);
    }

    @Override
    public void updateSeller(Long accountId) {
        iAccountRepository.updateSeller(accountId);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return iAccountRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        iAccountRepository.deleteById(id);
    }

    @Override
    public List<Account> checkUserName(String userName) {
        return iAccountRepository.checkUserName(userName);
    }
}
