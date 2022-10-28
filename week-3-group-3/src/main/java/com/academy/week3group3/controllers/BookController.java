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

    // TODO: Show auditing upon return
    // TODO: Add paging and sorting in getting all authors
    // TODO: Add exception

    @Autowired
    private BookService bookService;


    // Adding book
    @PostMapping("/{authorId}/books")
    public Book saveBook(@RequestBody Book book, @PathVariable Long authorId) {
        return bookService.saveBook(book, authorId);
    }

    // Getting all books by author id
    // TODO: Work here
    @GetMapping
    public ResponseEntity<Page<Book>> getBooks(Pageable pageable) {
        Page<Book> book = bookService.findAllBooks(pageable);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // Getting book by book id
    // TODO: Work here
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) throws RecordNotFoundException {
        return bookService.findBookById(id);
    }

    // Updating book
    // TODO: Work here
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    // Deleting book
    // TODO: Work here
    @DeleteMapping("")
    public void deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
    }

}
