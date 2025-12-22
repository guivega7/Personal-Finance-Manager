package com.example.financetracker.repository;

import com.example.financetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Long id(Long id);
}
