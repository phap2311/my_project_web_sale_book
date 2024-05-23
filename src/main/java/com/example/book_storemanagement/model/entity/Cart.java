package com.example.book_storemanagement.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Set;

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
    private String status;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToMany(mappedBy = "carts")
    private Set<Books> books;
}
