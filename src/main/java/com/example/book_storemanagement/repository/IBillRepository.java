package com.example.book_storemanagement.repository;

import com.example.book_storemanagement.model.entity.Bill;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IBillRepository extends JpaRepository<Bill, Long> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into bill (code,date_bill,payment,content,address,money,status)" +
            "select :#{#bill.code}, current_date(),:#{#bill.payment}, :#{#bill.content},:#{#bill.address},sum(c.total_price),'pending'" +
            "from cart c where c.account_id = :accountId")
    void saveBill(Bill bill, @Param("accountId") Long accountId);

    @Query(nativeQuery = true, value = "SELECT LAST_INSERT_ID();")
    Long getLastInsertedId();

    Bill findByCode(String code);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update bill set status = 'Đã thanh toán' ")
    void updateBill(Bill bill, Long id);

    // Bill findByCodeBill(String code);
}
