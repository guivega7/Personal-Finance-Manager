package com.example.financetracker.repository;

import com.example.financetracker.model.Expense;
import com.example.financetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser(User user);
}
