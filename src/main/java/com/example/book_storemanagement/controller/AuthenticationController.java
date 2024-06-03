package com.example.book_storemanagement.controller;

import com.example.book_storemanagement.config.service.JwtResponse;
import com.example.book_storemanagement.config.service.JwtService;
import com.example.book_storemanagement.config.service.UsersService;
import com.example.book_storemanagement.model.entity.Account;
import com.example.book_storemanagement.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Authentication authentication
                = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account currentAccount = usersService.findByUsername(account.getUsername());
        return ResponseEntity.ok(new JwtResponse(currentAccount.getId(), jwt, userDetails.getUsername(), userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @GetMapping()
    public ResponseEntity<List<Account>> findAllListUser() {
        List<Account> accountList = iAccountService.findAllUser();
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    @PostMapping("register/user")
    public ResponseEntity<Account> createUser(@RequestBody Account account) {
        Account saveAccount = iAccountService.saveUser(account);
        Long accountId = saveAccount.getId();
        account.setPassword(passwordEncoder.encode(account.getPassword())); // mã hóa password
        iAccountService.updateUser(accountId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("register/seller")
    public ResponseEntity<Account> createSeller(@RequestBody Account account) {
        Account saveAccount = iAccountService.saveUser(account);
        Long accountId = saveAccount.getId();
        account.setPassword(passwordEncoder.encode(account.getPassword())); // mã hóa password
        iAccountService.updateSeller(accountId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/checkUserName")
    public ResponseEntity<List<Account>> checkUserName(@RequestParam String username) {
        List<Account> list = iAccountService.checkUserName(username);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
