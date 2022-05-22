package com.library.borrowing.serviceImplementation;

import java.sql.Timestamp;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.library.borrowing.entity.*;
import com.library.borrowing.repository.*;

import com.library.borrowing.service.BorrowingService;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    private BorrowingRepository borrowingRepository;
    private BookRepository bookRepository;
    private ReaderRepository readerRepository;

    public BorrowingServiceImpl(BorrowingRepository borrowingRepository, BookRepository bookRepository,ReaderRepository readerRepository) {
        super();
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    
    @Override
    public Page<Borrowing> listAll(int pageNum, String sortField, String sortDir) {
		
		Pageable pageable = PageRequest.of(pageNum - 1, 5, 
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
									  : Sort.by(sortField).descending()
		);
		
		return borrowingRepository.findAll(pageable);
	}
    
    @Override
    public List<Borrowing> getAllBorrowing() {
        return borrowingRepository.findAll();
    }

    @Override
    public Borrowing borrow(Borrowing borrowing)  {
    	
        if (!readerRepository.findById(borrowing.getReaderId()).isPresent()){
            
            return null;    
        }
        Book book = bookRepository.findById(borrowing.getBookId()).get();
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        
        if (book != null && book.getNumberOfCopies() > 0) {
            book.removeCopy();
            borrowing.setStartTime(currentTimestamp.toString());
            borrowing.setStatus("On going");
            return borrowingRepository.save(borrowing);
        }
        return null;  
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
            Timestamp EndTimestamp = Timestamp.valueOf(b.getEndTime() + " 00:00:00");
            b.setActualReturnTime(currentTimestamp.toString());
            if (currentTimestamp.after(EndTimestamp)) {
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