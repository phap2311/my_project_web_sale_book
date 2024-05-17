package com.example.book_storemanagement.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "cart")
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private LocalDate date_purchase;
    private double price;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books books;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
