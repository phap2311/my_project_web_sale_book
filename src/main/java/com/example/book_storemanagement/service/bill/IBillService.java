package com.example.book_storemanagement.service.bill;

import com.example.book_storemanagement.model.entity.Bill;

import java.util.Optional;

public interface IBillService {
    Bill save (Bill bill, Long accountId);
    Long getLastInsertedId();
    Optional<Bill>findById(Long id);
    Bill findByCodeBill(String code);

    void update(Bill bill, Long id);
}
