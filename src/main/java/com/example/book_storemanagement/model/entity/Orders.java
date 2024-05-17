package com.example.book_storemanagement.model.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate order_date;
    private String status;
    private double total_amount;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
