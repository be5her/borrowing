package com.library.borrowing.controller.web;

import com.library.borrowing.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.library.borrowing.entity.*;

@Controller
public class BookWeb {

    @Autowired
    BookService bookService;

    @GetMapping("books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.getAllBook());
        return "book/book";
    }

    @GetMapping("books/new")
    public String getNewBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "book/create_book";
    }

    @PostMapping("books/new")
    public String saveNewBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("books/edit/{id}")
    public String updateBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "book/edit_book";
    }

    @PostMapping("books/edit/{id}")
    public String saveReade(@PathVariable Long id, @ModelAttribute("book") Book book, Model model) {
        book.setId(id);
        bookService.updateBook(book);
        return "redirect:/books";
    }

    @GetMapping("books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
}
