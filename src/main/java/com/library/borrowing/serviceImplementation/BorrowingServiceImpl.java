package com.library.borrowing.serviceImplementation;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.borrowing.entity.*;
import com.library.borrowing.repository.*;

import com.library.borrowing.service.BorrowingService;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    private BorrowingRepository borrowingRepository;
    private BookRepository bookRepository;

    public BorrowingServiceImpl(BorrowingRepository borrowingRepository, BookRepository bookRepository) {
        super();
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Borrowing> getAllBorrowing() {
        return borrowingRepository.findAll();
    }

    @Override
    public Borrowing borrow(Borrowing borrowing) {
        Book book = bookRepository.findById(borrowing.getBookId()).get();
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        if (book != null && book.getNumberOfCopies() > 0) {
            book.removeCopy();
            borrowing.setStartTime(currentTimestamp);
            borrowing.setStatus("On going");
            return borrowingRepository.save(borrowing);
        }
        return borrowingRepository.save(null);
    }

    @Override
    public Borrowing getBorrowingById(Long id) {
        return borrowingRepository.findById(id).get();
    }

    @Override
    public Borrowing updateBorrowing(Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    @Override
    public void returnBook(Long id) {
        Borrowing b = borrowingRepository.findById(id).get();
        if (b != null && b.getStatus().equals("On going")) {
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            b.setActualReturnTime(currentTimestamp);
            if (currentTimestamp.after(b.getEndTime())) {
                b.setStatus("Late Returned");
            } else {
                b.setStatus("Returned");
            }
            Book book = bookRepository.findById(b.getBookId()).get();
            book.addCopy();
            borrowingRepository.flush();
        }
    }
}