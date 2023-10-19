package com.basic.books.controller;

import com.basic.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String getBookList(Model model){

        return "forward:/bookWithThymeleaf/bookList";
    }

}
