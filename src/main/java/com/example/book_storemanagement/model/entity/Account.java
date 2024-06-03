package com.example.book_storemanagement.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String  name;
    private String phone;
    private String email;
    private String avatar;
    private String address;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> roles;
}
