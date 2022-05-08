package com.library.borrowing.service;

import java.util.List;

import com.library.borrowing.entity.Book;

public interface BookService {

    public List<Book> getAllBook();

    public Book saveBook(Book book);

    public Book getBookById(Long id);

    public Book updateBook(Book book);

    public void deleteBookById(Long id);

}