package com.basic.books.service;

import com.basic.books.dto.BookReqDTO;
import com.basic.books.dto.BookResDTO;
import com.basic.books.entity.Book;
import com.basic.books.exception.BusinessException;
import com.basic.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookResDTO create(BookReqDTO bookReqDTO){
        Book book = modelMapper.map(bookReqDTO,Book.class);
        return modelMapper.map(bookRepository.save(book),BookResDTO.class);
    }
    public List<BookResDTO> getBooks(){
        return bookRepository
                .findAll()
                .stream()
                .map(bookEntity -> modelMapper.map(bookEntity, BookResDTO.class))
                .collect(Collectors.toList());
    }
    public BookResDTO getBook(long id){
        Book book = bookRepository.findById(id).orElseThrow(
                ()->new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        return modelMapper.map(book, BookResDTO.class);
    }

    public BookResDTO getBooksByTitle(String title){
        Book book =  bookRepository.findByTitle(title).orElseThrow(
                ()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
        return modelMapper.map(book, BookResDTO.class);
    }

    public BookResDTO getBooksByAuthor(String author){
        Book book =  bookRepository.findByAuthor(author).orElseThrow(
                ()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
        return modelMapper.map(book, BookResDTO.class);
    }

    public BookResDTO getBooksByIsbn(String isbn){
        Book book =  bookRepository.findByIsbn(isbn).orElseThrow(
                ()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
        return modelMapper.map(book, BookResDTO.class);
    }

    public BookResDTO getBooksByGenre(String genre){
        Book book =  bookRepository.findByGenre(genre).orElseThrow(
                ()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
        return modelMapper.map(book, BookResDTO.class);
    }

    public ResponseEntity<?> deleteBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
        return ResponseEntity.ok("delete book success");
    }

    public BookResDTO updateBook(Long id, BookReqDTO bookReqDTO){
        Book book = bookRepository.findById(id).orElseThrow(()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
        book.setTitle(bookReqDTO.getTitle());
        book.setAuthor(bookReqDTO.getAuthor());
        book.setGenre(bookReqDTO.getGenre());
        return modelMapper.map(book,BookResDTO.class);
    }



}



