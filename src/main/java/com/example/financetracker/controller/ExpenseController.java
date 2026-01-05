package com.example.financetracker.controller;

import com.example.financetracker.model.Expense;
import com.example.financetracker.model.User;
import com.example.financetracker.repository.ExpenseRepository;
import com.example.financetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseRepository repository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String listExpenses(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        
        User currentUser = userRepository.findByUsername(principal.getName());
        List<Expense> expenses = repository.findByUser(currentUser);

        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalExpense = BigDecimal.ZERO;
        
        for (Expense item : expenses) {
            if (item.getAmount() != null){
                if(item.isTypeOfAmount()){
                    totalIncome = totalIncome.add(item.getAmount());
                } else {
                    totalExpense = totalExpense.add(item.getAmount());
                }
            }
        }

        model.addAttribute("expenses", expenses);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("balance", totalIncome.subtract(totalExpense));

        return "index";
    }

    @PostMapping("/save")
    public String createExpense(@ModelAttribute Expense expense, Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName());
        expense.setUser(currentUser);

        if (expense.getDate() == null || expense.getDate().isEmpty()) {
            expense.setDate(LocalDate.now().toString());
        }

        repository.save(expense);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}