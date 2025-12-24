package com.example.financetracker.controller;

import com.example.financetracker.model.Expense;
import com.example.financetracker.model.User;
import com.example.financetracker.repository.ExpenseRepository;
import com.example.financetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseRepository repository;

    @Autowired
    private UserRepository userRepository;

    // This handles the main dashboard at http://localhost:8080/
    @GetMapping("/")
    public String listExpenses(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        User currentUser = userRepository.findByUsername(principal.getName());
        model.addAttribute("expenses", repository.findByUser(currentUser));

        return "index";
    }

    @PostMapping("/save")
    public String createExpense(@ModelAttribute Expense expense, Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName());
        expense.setUser(currentUser);

        // If the date is empty in the form, we set it to today's date so the app doesn't crash
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