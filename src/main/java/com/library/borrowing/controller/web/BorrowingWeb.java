package com.library.borrowing.controller.web;

import com.library.borrowing.service.BorrowingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.library.borrowing.entity.*;

@Controller
public class BorrowingWeb {

    @Autowired
    BorrowingService borrowingService;

    @GetMapping("borrowings")
    public String getBorrowings(Model model) {
        model.addAttribute("borrowings", borrowingService.getAllBorrowing());
        return "borrowing/borrowing";
    }

    @GetMapping("borrowings/new")
    public String getNewBorrowing(Model model) {
        Borrowing borrowing = new Borrowing();
        model.addAttribute("borrowing", borrowing);
        return "borrowing/create_borrowing";
    }

    @PostMapping("borrowings/new")
    public String saveNewBorrowing(@ModelAttribute("borrowing") Borrowing borrowing) {
        borrowingService.borrow(borrowing);
        return "redirect:/borrowing";
    }

    @GetMapping("borrowings/return/{id}")
    public String returnBorrowing(@PathVariable Long id) {
        borrowingService.returnBook(id);
        return "redirect:/borrowing";
    }
}
