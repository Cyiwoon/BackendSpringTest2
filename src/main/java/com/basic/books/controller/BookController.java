package com.basic.books.controller;

import com.basic.books.dto.BookReqDTO;
import com.basic.books.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/bookWithThymeleaf")
public class BookController {
    private final BookService bookService;

    @GetMapping(value = "/bookList")
    public String getBookList(Model model) {
        model.addAttribute("bookList", bookService.getBooks());
        return "index";
    }

    @GetMapping(value = "/book/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBook(id));
        return "book";
    }

    @GetMapping(value = "/add")
    public String gotoBookAddPage(BookReqDTO bookReqDTO) {
//        return "book-add";
        return "book-add";

    }

    @PostMapping(value = "/add")
    public String addBook(@Valid BookReqDTO bookReqDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "book-add";
        }
        bookService.create(bookReqDTO);
        return "redirect:/";
    }

    @GetMapping(value = "/update/{id}")
    public String gotoUpdateBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBook(id));
        return "book-update";
    }


    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid BookReqDTO bookReqDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(">>>hasEroors book " + bookReqDTO);
            model.addAttribute("book", bookReqDTO);
            return "book-update";
        }
        bookService.updateBook(id, bookReqDTO);
        return "redirect:/";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/";
    }


}
