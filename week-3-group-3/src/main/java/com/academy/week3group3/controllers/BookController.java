package com.academy.week3group3.controllers;

import com.academy.week3group3.exceptions.RecordNotFoundException;
import com.academy.week3group3.model.Book;
import com.academy.week3group3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class BookController {

    @Autowired
    private BookService bookService;


    // Adding book
    @PostMapping("/{authorId}/books")
    public Book saveBook(@RequestBody Book book, @PathVariable Long authorId) {
        return bookService.saveBook(book, authorId);
    }

    // Getting all books by author id
    @GetMapping("/{authorId}/books")
    public ResponseEntity<Page<Book>> getBooksByAuthorId(@PathVariable Long authorId, Pageable pageable) {
        Page<Book> book = bookService.findAllBooks(authorId, pageable);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // Getting book by book id
    @GetMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<Page<Book>> getBookByBookId(
            @PathVariable Long authorId,
            @PathVariable Long bookId,
            Pageable pageable
    ) throws RecordNotFoundException {
        Page<Book> book = bookService.findBookById(authorId, bookId, pageable);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // Update book by id
    @PutMapping("/{authorId}/books/{bookId}")
    public Book updateBook(
            @PathVariable Long authorId,
            @PathVariable Long bookId,
            @RequestBody Book book
    ) throws RecordNotFoundException {
        return bookService.updateBook(authorId, bookId, book);
    }

    // Delete book by id
    @DeleteMapping("/{authorId}/books")
    public void deleteBook(@PathVariable Long authorId, @RequestParam Long id) throws RecordNotFoundException {
        bookService.deleteBook(authorId, id);
    }

}
