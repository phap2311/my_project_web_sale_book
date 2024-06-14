package com.example.book_storemanagement.controller;

import com.example.book_storemanagement.model.entity.Bill;
import com.example.book_storemanagement.model.entity.Cart;
import com.example.book_storemanagement.service.bill.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bill/")
public class BillController {
    @Autowired
    private IBillService iBillService;

    @PostMapping("create/{accountId}")
    public ResponseEntity<Long> createBill(@RequestBody Bill bill, @PathVariable Long accountId) {
        iBillService.save(bill, accountId);
        Long billId = iBillService.getLastInsertedId();
        return new ResponseEntity<>(billId, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        Optional<Bill> billOptional = iBillService.findById(id);
        if (!billOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(billOptional.get(), HttpStatus.OK);
    }

}
