package com.example.book_storemanagement.model.dto;

import java.time.LocalDate;

public class CartDTO {
   private Long id;

    private String image;

    private String author;

    private String name;

    private String quantity;

    private LocalDate date_purchase;

    private double total_price;
    private Long billId;
    private Long books_id;

    public CartDTO() {
    }

    public CartDTO(Long id, String image, String author, String name, String quantity, LocalDate date_purchase, double total_price, Long billId, Long books_id) {
        this.id = id;
        this.image = image;
        this.author = author;
        this.name = name;
        this.quantity = quantity;
        this.date_purchase = date_purchase;
        this.total_price = total_price;
        this.billId = billId;
        this.books_id = books_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate_purchase() {
        return date_purchase;
    }

    public void setDate_purchase(LocalDate date_purchase) {
        this.date_purchase = date_purchase;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getBooks_id() {
        return books_id;
    }

    public void setBooks_id(Long books_id) {
        this.books_id = books_id;
    }
}
