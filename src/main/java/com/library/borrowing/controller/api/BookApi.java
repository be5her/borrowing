package com.library.borrowing.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.library.borrowing.entity.Book;
import com.library.borrowing.service.BookService;

@RestController
public class BookApi {

    @Autowired
    BookService bookService;

    @GetMapping("/api/json/books")
    public List<Book> getBooksApi() {
    	
        return bookService.getAllBook();
    }

    @GetMapping("/api/json/books/{id}")
    public Book getBooksApi(@PathVariable Long id) {

        return bookService.getBookById(id);
    }

    @PostMapping("/api/json/books")
    public Book saveBooksApi(@RequestBody Book book) {

        return bookService.saveBook(book);
    }

    @PutMapping("/api/json/books/{id}")
    public Book updateBooksApi(@RequestBody Book book, @PathVariable Long id) {
        book.setId(id);
        return bookService.updateBook(book);
    }

    @DeleteMapping("/api/json/books/{id}")
    public void deleteBooksApi(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

}
