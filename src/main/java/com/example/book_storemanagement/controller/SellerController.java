package com.example.book_storemanagement.controller;

import com.example.book_storemanagement.config.service.JwtService;
import com.example.book_storemanagement.model.dto.SellerDetail;
import com.example.book_storemanagement.model.entity.Books;
import com.example.book_storemanagement.repository.IAccountRepository;
import com.example.book_storemanagement.service.seller.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/seller")
public class SellerController {
    @Autowired
    private ISellerService iSellerService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IAccountRepository iAccountRepository;

    @GetMapping("/list")
    public ResponseEntity<List<SellerDetail>> getAllSeller(@RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.substring(7);
        String account = jwtService.getUsernameFromJwtToken(token);
        iAccountRepository.findByUsername(account);
        List<SellerDetail> sellerDetails = iSellerService.getAllSeller();
        return new ResponseEntity<>(sellerDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDetail> getSellerDetail(@PathVariable Long id) {
        Optional<SellerDetail> sellerDetailOptional = iSellerService.getSellerDetail(id);
        if (!sellerDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sellerDetailOptional.get(), HttpStatus.OK);
    }


}
