package com.example.book_storemanagement.model.dto;

import java.time.LocalDate;

public class BillDTO {
    private String code;
    private LocalDate date_bill;
    private String payment;
    private String content;
    private String address;
    private double money;

    public BillDTO(String code, LocalDate date_bill, String payment, String content, String address, double money) {
        this.code = code;
        this.date_bill = date_bill;
        this.payment = payment;
        this.content = content;
        this.address = address;
        this.money = money;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDate_bill() {
        return date_bill;
    }

    public void setDate_bill(LocalDate date_bill) {
        this.date_bill = date_bill;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
