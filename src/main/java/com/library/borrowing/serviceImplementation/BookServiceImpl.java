package com.library.borrowing.serviceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.borrowing.entity.Book;
import com.library.borrowing.repository.BookRepository;
import com.library.borrowing.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        super();
        this.bookRepository = bookRepository;
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