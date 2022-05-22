package com.library.borrowing.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.library.borrowing.entity.Borrowing;

public interface BorrowingService {

    public List<Borrowing> getAllBorrowing();

    public Page<Borrowing> listAll(int pageNum, String sortField, String sortDir);

    public Borrowing borrow(Borrowing borrowing);

    public Borrowing getBorrowingById(Long id);

    public Borrowing updateBorrowing(Borrowing borrowing);

    public void returnBook(Long id);

}