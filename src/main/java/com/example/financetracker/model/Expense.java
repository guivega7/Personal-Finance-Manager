package com.example.financetracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Description is required")
    private String description;

    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Date is required")
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean typeOfAmount;

    //Geters and Setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isTypeOfAmount() {
        return typeOfAmount;
    }

    public void setTypeOfAmount(boolean typeOfAmount) {
        this.typeOfAmount = typeOfAmount;
    }
}



