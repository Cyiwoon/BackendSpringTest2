package com.basic.books.controller;

import com.basic.books.dto.BookReqDTO;
import com.basic.books.dto.BookResDTO;
import com.basic.books.entity.Book;
import com.basic.books.exception.BusinessException;
import com.basic.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;//서비스들은 다 DTO출력

    @PostMapping
    public BookResDTO create(@RequestBody BookReqDTO bookReqDTO){
        return bookService.create(bookReqDTO);
    }

    @RequestMapping
    public List<BookResDTO> getBook(){
        return bookService.getBooks();
    }

    @RequestMapping(value = "/{id}")
    public BookResDTO getBook(@PathVariable Long id){
        return bookService.getBook(id);
    }


    @RequestMapping(value = "/title/{title}", produces = {"application/json"})
    public BookResDTO getBooksByTitle(@PathVariable String title){
        return bookService.getBooksByTitle(title);
    }

    @RequestMapping(value = "/author/{author}", produces = {"application/json"})
    public BookResDTO getBooksByAuthor(@PathVariable String author){
        return bookService.getBooksByAuthor(author);
    }

    @RequestMapping(value = "/isbn/{isbn}", produces = {"application/json"})
    public BookResDTO getBooksByIsbn(@PathVariable String isbn){
        return bookService.getBooksByIsbn(isbn);
    }

    @RequestMapping(value = "/genre/{genre}", produces = {"application/json"})
    public BookResDTO getBooksByGenre(@PathVariable String genre){
        return bookService.getBooksByGenre(genre);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("delete book success");
    }

    @PatchMapping(value = "/{id}")
    public BookResDTO updateBooks(@PathVariable Long id, @RequestBody BookReqDTO bookReqDTO){
        return bookService.updateBook(id, bookReqDTO);
    }

}
