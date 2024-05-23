package com.example.book_storemanagement.model.dto;

import java.time.LocalDate;

public interface CartDTO {
    Long getId();

    String getName();

    String getQuantity();

    LocalDate getDate_purchase();

    double getTotal_price();
}
