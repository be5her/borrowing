package com.library.borrowing.controller.api;

import com.library.borrowing.entity.Borrowing;
import com.library.borrowing.service.BorrowingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class BorrowingApi {

    @Autowired
    private BorrowingService borrowingService;

    @GetMapping("/api/json/borrowings")
    public List<Borrowing> getBorrowingApi() {

        return borrowingService.getAllBorrowing();
    }

    @GetMapping("api/json/borrowings/{id}")
    public Borrowing getBorrowingApi(@PathVariable Long id) {
        return borrowingService.getBorrowingById(id);
    }

    @PostMapping("api/json/borrowings")
    public Borrowing saveBorrowingApi(@RequestBody Borrowing borrowing) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
    	return borrowingService.borrow(borrowing);
    }

    @PutMapping("api/json/borrowings/{id}")
    public void returnBorrowingApi(@PathVariable Long id) {
        System.out.println("entered api");
        borrowingService.returnBook(id);
    }

}
