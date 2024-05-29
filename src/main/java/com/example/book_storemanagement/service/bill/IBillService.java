package com.example.book_storemanagement.service.bill;

import com.example.book_storemanagement.model.entity.Bill;

import java.util.Optional;

public interface IBillService {
    void save (Bill bill, Long accountId);
    Optional<Bill>findById(Long id);
}
