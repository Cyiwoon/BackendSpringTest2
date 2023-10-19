package com.basic.books.controller;

import com.basic.books.entity.Book;
import com.basic.books.exception.BusinessException;
import com.basic.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookSimpleRestController {

    @Autowired
    private BookRepository bookRepository;//다 entity출력

    @PostMapping
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }


    @RequestMapping(produces = {"application/json"})
    //HTTP GET 요청을 처리하고 JSON 응답을 생성하는 메서드를 나타냅니다.
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/{id}")
    public Book getBook(@PathVariable Long id){
        return bookRepository.findById(id).orElseThrow(
                ()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/title/{title}", produces = {"application/json"})
    public Book getBooksByTitle(@PathVariable String title){
        return bookRepository.findByTitle(title).orElseThrow(
                ()->new BusinessException("Books Not Found",HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/author/{author}", produces = {"application/json"})
    public Book getBooksByAuthor(@PathVariable String author){
        return bookRepository.findByAuthor(author).orElseThrow(
                ()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/isbn/{isbn}", produces = {"application/json"})
    public Book getBooksByIsbn(@PathVariable String isbn){
        return bookRepository.findByIsbn(isbn).orElseThrow(
                ()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/genre/{genre}", produces = {"application/json"})
    public Book getBooksByGenre(@PathVariable String genre){
        return bookRepository.findByGenre(genre).orElseThrow(
                ()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        Book book = bookRepository.findById(id).orElseThrow(
                ()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
        return ResponseEntity.ok("delete book success");
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetail) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));

        //수정하려는 값을 저장
        book.setTitle(bookDetail.getTitle());
        book.setAuthor(bookDetail.getAuthor());
        book.setIsbn(bookDetail.getIsbn());
        book.setGenre(bookDetail.getGenre());
        Book updatedBook = bookRepository.save(book);
        return updatedBook;
    }

}