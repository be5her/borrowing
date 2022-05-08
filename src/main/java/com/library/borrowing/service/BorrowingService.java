package com.library.borrowing.service;

import java.util.List;

import com.library.borrowing.entity.Borrowing;

public interface BorrowingService {

    public List<Borrowing> getAllBorrowing();

    public Borrowing borrow(Borrowing borrowing);

    public Borrowing getBorrowingById(Long id);

    public Borrowing updateBorrowing(Borrowing borrowing);

    public void returnBook(Long id);

}