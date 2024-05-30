package com.example.book_storemanagement.config.service;


import com.example.book_storemanagement.config.UsersPrinciple;
import com.example.book_storemanagement.model.entity.Account;
import com.example.book_storemanagement.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private IAccountRepository iAccountRepository;


    public Account findByUsername(String name) {
        return iAccountRepository.findByUsername(name);
    }

    public UserDetails loadUserByUsername(String username) {
        return UsersPrinciple.build(iAccountRepository.findByUsername(username));
    }


}
