package com.example.book_storemanagement.service.bill;

import com.example.book_storemanagement.model.dto.BillDTO;
import com.example.book_storemanagement.model.entity.Bill;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IBillService {
    Bill save (Bill bill, Long accountId);
    Long getLastInsertedId();
    Optional<Bill>findById(Long id);
    Bill findByCodeBill(String code);

    void update( Long accountId, Long billId);

    List<Bill> getAllBillByAccount(Long accountId);

}
