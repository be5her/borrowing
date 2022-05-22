package com.library.borrowing.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ApiWeb {
	@RequestMapping("api")
    public String getApi(Model model) {
        
        return "api";
    }
}
