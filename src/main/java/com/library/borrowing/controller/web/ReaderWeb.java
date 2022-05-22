package com.library.borrowing.controller.web;

import com.library.borrowing.service.ReaderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.library.borrowing.entity.*;

@Controller
public class ReaderWeb {

    @Autowired
    ReaderService readerService;

    @GetMapping("readers")
    public String getReaders(Model model) {
       
        return viewPage(model, 1, "fullName", "asc");
    }

    
    @GetMapping("readers/{pageNum}")
    public String viewPage(Model model, 
			@PathVariable(name = "pageNum") int pageNum,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir) {
    	
    	Page<Reader> page = readerService.listAll(pageNum, sortField, sortDir);
		
		List<Reader> readers = page.getContent();
		
		model.addAttribute("currentPage", pageNum);		
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("readers", readers);
    	
    	
        return "reader/reader";
    }
    
    
    @GetMapping("readers/new")
    public String getNewReader(Model model) {
        Reader reader = new Reader();
        model.addAttribute("reader", reader);
        return "reader/create_reader";
    }

    @PostMapping("readers/new")
    public String saveNewReader(@ModelAttribute("reader") Reader reader) {
        readerService.saveReader(reader);
        return "redirect:/readers";
    }

    @GetMapping("readers/edit/{id}")
    public String updateReader(@PathVariable Long id, Model model) {
        model.addAttribute("reader", readerService.getReaderById(id));
        return "reader/edit_reader";
    }

    @PostMapping("readers/edit/{id}")
    public String saveReade(@PathVariable Long id, @ModelAttribute("reader") Reader reader, Model model) {
        reader.setId(id);
        readerService.updateReader(reader);
        return "redirect:/readers";
    }

    @GetMapping("readers/delete/{id}")
    public String deleteReader(@PathVariable Long id) {
        readerService.deleteReaderById(id);
        return "redirect:/readers";
    }
}
