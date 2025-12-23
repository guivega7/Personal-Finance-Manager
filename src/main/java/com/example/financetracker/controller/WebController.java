package com.example.financetracker.controller;

import com.example.financetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {
    @Autowired
    private ExpenseRepository repository;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("expenses", repository.findAll());
        return "index";
    }
}
