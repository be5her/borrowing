package com.library.borrowing.controller.web;

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

@Controller
public class BorrowingWeb {

    @Autowired
    BorrowingService borrowingService;

    @GetMapping("borrowings")
    public String getBorrowings(Model model) {
    	return viewPage(model, 1, "status", "asc");
    }
    
    @RequestMapping("borrowings/{pageNum}")
    public String viewPage(Model model, 
			@PathVariable(name = "pageNum") int pageNum,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir) {
    	
    	Page<Borrowing> page = borrowingService.listAll(pageNum, sortField, sortDir);
		
		List<Borrowing> borrowings = page.getContent();
		
		model.addAttribute("currentPage", pageNum);		
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("borrowings", borrowings);
		
    	
    	
        return "borrowing/borrowing";
    }
    

    @GetMapping("borrowings/new")
    public String getNewBorrowing(Model model) {
        Borrowing borrowing = new Borrowing();
        model.addAttribute("borrowing", borrowing);
        return "borrowing/create_borrowing";
    }

    @PostMapping("borrowings/new")
    public String saveNewBorrowing(@ModelAttribute("borrowing") Borrowing borrowing) {
        
        borrowingService.borrow(borrowing);
        return "redirect:/borrowings";
    }

    @GetMapping("borrowings/return/{id}")
    public String returnBorrowing(@PathVariable Long id) {
        borrowingService.returnBook(id);
        return "redirect:/borrowings";
    }
}
