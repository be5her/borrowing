package com.library.borrowing.controller.web;

import com.library.borrowing.service.ReaderService;

import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("readers", readerService.getAllReaders());
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
