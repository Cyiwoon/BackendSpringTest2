package com.basic.books.repository;

import com.basic.books.controller.BookSimpleRestController;
import com.basic.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    Optional<Book> findByAuthor(String author);
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByGenre(String genre);

}
