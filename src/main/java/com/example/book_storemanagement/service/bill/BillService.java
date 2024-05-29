package com.example.book_storemanagement.service.bill;

import com.example.book_storemanagement.model.entity.Bill;
import com.example.book_storemanagement.repository.IBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService implements IBillService {
    @Autowired
    private IBillRepository iBillRepository;

    @Override
    public void save(Bill bill,Long accountId) {
         iBillRepository.saveBill(bill, accountId);
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return iBillRepository.findById(id);
    }
}
