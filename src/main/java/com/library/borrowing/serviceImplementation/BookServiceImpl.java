package com.library.borrowing.serviceImplementation;

import java.util.List;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.library.borrowing.entity.Book;
import com.library.borrowing.repository.BookRepository;
import com.library.borrowing.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> listAll(int pageNum, String sortField, String sortDir) {
		
		Pageable pageable = PageRequest.of(pageNum - 1, 5, 
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
									  : Sort.by(sortField).descending()
		);
		
		return bookRepository.findAll(pageable);
	}
    
    @Override
    public List<Book> getAllBook() {
    	 return bookRepository.findAll();
	}

    @Override
    public Book saveBook(Book book) {
        if (book.getNumberOfCopies() == 0) {
            book.setNumberOfCopies(1);
        }
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

}