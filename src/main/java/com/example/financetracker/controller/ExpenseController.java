package com.example.financetracker.controller;

import com.example.financetracker.model.Expense;
import com.example.financetracker.repository.ExpenseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository repository;

    @GetMapping
    public List<Expense> listAllExpensives(){
        return repository.findAll();
    }

    @PostMapping
    public Expense createExpense(@Valid @RequestBody Expense expense){
        return repository.save(expense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@Valid @PathVariable Long id, @RequestBody Expense expense){
        expense.setId(id);
        return repository.save(expense);
    }
}
