package com.library.borrowing.controller.web;

import com.library.borrowing.service.BookService;
import com.library.borrowing.service.BorrowingService;


import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.library.borrowing.entity.*;

import java.sql.Timestamp;



@Controller
public class BookWeb {

    @Autowired
    BookService bookService;
    @Autowired
    BorrowingService borrowingService;
    @RequestMapping(value = { "/", "books" })
    public String getBooks(Model model) {
        return viewPage(model, 1, "bookName", "asc");
    }
    
    @RequestMapping("books/{pageNum}")
    public String viewPage(Model model, 
			@PathVariable(name = "pageNum") int pageNum,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir) {
    	
    	Page<Book> page = bookService.listAll(pageNum, sortField, sortDir);
		
		List<Book> books = page.getContent();
		
		model.addAttribute("currentPage", pageNum);		
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("books", books);
		
    	
    	
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


    @GetMapping("books/borrow/{id}")
    public String borrowBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        Borrowing borrowing = new Borrowing();
       
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        borrowing.setBookId(id);
        borrowing.setStartTime(currentTimestamp.toString());
        borrowing.setActualReturnTime(null);
        borrowing.setStatus("On going");

        model.addAttribute("borrowing", borrowing);
        
        return "book/borrow_book";
    }

    @PostMapping("books/borrow/{id}")
    public String saveborrow(@PathVariable Long id,@ModelAttribute("borrowing") Borrowing borrowing,
     @ModelAttribute("book") Book book, Model model  ) {

        //Borrowing borrowingTemp = new Borrowing();
        //borrowing.setId(borrowingTemp.getId());
        borrowingService.borrow(borrowing);

        return "redirect:/books";
    }

    @GetMapping("books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
}
