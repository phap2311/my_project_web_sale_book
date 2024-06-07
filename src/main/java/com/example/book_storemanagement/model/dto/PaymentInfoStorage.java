package com.example.book_storemanagement.model.dto;

import com.example.book_storemanagement.model.entity.Bill;
import org.springframework.stereotype.Component;

@Component
public class PaymentInfoStorage {
    private Bill bill;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
