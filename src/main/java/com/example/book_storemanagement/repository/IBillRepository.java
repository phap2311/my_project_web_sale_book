package com.example.book_storemanagement.repository;

import com.example.book_storemanagement.model.entity.Bill;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBillRepository extends JpaRepository<Bill, Long> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into bill (date_bill,payment,content,address,money)" +
            "select current_date(),:#{#bill.payment}, :#{#bill.content},:#{#bill.address},sum(c.total_price)" +
            "from cart c where c.account_id = :accountId")
    void saveBill(Bill bill, @Param("accountId") Long accountId);
}
